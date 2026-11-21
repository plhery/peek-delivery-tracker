---
title: Suivi ePost Global : numéros EPG, statuts et livraison
description: Suis un colis ePost Global : format des numéros EPG, sens des étapes en anglais, transporteurs partenaires en France, Belgique et Suisse et suivi avec Peek.
slug: suivi-epost-global
published: 2026-11-21
updated: 2026-11-21
---

ePost Global (`epostglobalshipping.com`) est un consolidateur international de colis et de courrier basé aux États-Unis (`11137 Warland Drive, Cypress, CA 90630, USA`). Issu de la division *Express Postal Options (XPO)* de R.R. Donnelley et fort de plus de 25 ans d'activité transfrontalière, il regroupe les envois de boutiques en ligne dans ses centres de tri américains, organise le transport aérien et le dédouanement, puis confie la livraison finale à un réseau de plus de 100 postes et transporteurs dans plus de 200 pays et territoires (et assure des livraisons domestiques aux États-Unis et au Canada via `eDGE`). Ses numéros commencent par `EPG` : colle le tien dans le champ ci-dessus. Ses propres portails de suivi sont `epgtrack.com` (*EPG Track*) et `ppxtrack.com` (*PPX Track*) ; dans le catalogue de Peek, la recherche par `ePostGlobal`, `EPG`, `PPX Track`, `PPXTrack`, `RR Donnelley`, `RRD Donnelley` ou `RRD International Logistics` trouve aussi ePost Global.

## Numéro de suivi ePost Global : à quoi il ressemble

Peek reconnaît un seul format propre à ePost Global : les lettres `EPG` suivies de 10 à 15 chiffres (les étiquettes actuelles en comptent 15). Avec des chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `EPG482910573649182` | `EPG` et 15 chiffres : un numéro ePost Global actuel. |
| `EPG4829105736` | `EPG` et 10 chiffres : également un numéro ePost Global, la longueur minimale acceptée par Peek. |
| `EPG482910573` | Seulement 9 chiffres : ce n'est pas le format d'ePost Global. Trois lettres et neuf chiffres correspondent à [Colis Privé](carrier:colis-prive), [Purolator](carrier:purolator) et TNT Australia, que Peek interroge alors. Si tu choisis ePost Global manuellement, Peek indique qu'il « n’émet pas de numéros comme celui-ci ». Vérifie s'il manque un chiffre. |
| `EPG4829105736491827` | 16 chiffres, soit un de trop : Peek ne reconnaît aucun transporteur. Vérifie s'il y a un chiffre en trop. |
| `LZ482910576US` | Deux lettres, neuf chiffres et `US` : un numéro postal américain. Peek désigne [USPS](carrier:usps) pour la plupart de ceux qui commencent par `C`, `E`, `L` ou `R` (selon la FAQ d'ePost Global, le suivi se fait sur le portail PPX ou sur celui d'USPS selon le produit). Pour voir aussi les étapes propres à ePost Global dans Peek, ajoute le numéro `EPG`. |

Les espaces, tirets et minuscules n'ont pas d'importance quand tu colles un numéro dans Peek. Sur `epgtrack.com`, tu peux saisir jusqu'à 20 numéros séparés par des espaces, virgules, points-virgules, tabulations ou retours à la ligne ; sur `ppxtrack.com`, la limite est de 10 numéros.

**Où le trouver.** Dans l'e-mail d'expédition de la boutique ou sur la page de ta commande. Si tu ne le trouves pas, consulte [comment trouver son numéro de suivi](guide:find-tracking-number) et [les formats des numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi ePost Global expliqués

Le traceur d'ePost Global affiche chaque ligne en anglais avec une date, une heure, un intitulé et, le plus souvent, un lieu. Voici d'abord les étapes propres à ePost Global, dans l'ordre du parcours :

| ePost Global indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Data Received` | La boutique a transmis les données du colis à ePost Global, qui n'a pas encore reçu le paquet. | « Étiquette créée » |
| `Shipment in Transit to the ePost Global Processing Center` | Ajouté systématiquement une minute après `Data Received` : cela fait partie de la création de l'étiquette et ne prouve pas encore que le colis roule. | « Étiquette créée » |
| `Parcel Received and Processing` | Un centre de traitement américain d'ePost Global a scanné le colis (avec sa ville et son État). ePost Global indique traiter et réexpédier les colis en 24 heures ou moins. | « Pris en charge » |
| `Parcel Shipped` | Le colis a quitté le centre de traitement américain. | « En transit » |
| `In transit to … tracking #: …` | Remise au partenaire qui prend le relais, une minute après l'expédition, avec le nom du service et le numéro de suivi du partenaire (parfois écrit `In transit via …` ou `tracking#:`). | « En transit » |
| `In transit` | Même remise au partenaire, sans mention du service ni du second numéro. | « En transit » |

**Les lignes relayées des partenaires.** Après cette remise, `epgtrack.com` relaie les événements du transporteur partenaire. Ceux que le code de Peek associe directement :

| Après la remise | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Shipping label created - OSM awaiting item` | Le partenaire américain OSM Worldwide a reçu les informations du colis. | aucun recul |
| `Arrived at OSM facility`, `Departed OSM facility`, `Package accepted at courier partner`, `Package processed by courier partner` | Passage dans un centre OSM Worldwide puis remise à son propre partenaire de distribution. | « En transit » |
| `Electronic manifest received` | Manifeste électronique enregistré chez ECMS Express après la réception ePost Global : simple donnée sans lieu, qui ne change pas l'étape en cours. | aucun changement |
| `Parcel has arrived at export distribution warehouse` | Le colis est arrivé à l'entrepôt d'exportation d'ECMS Express au départ. | « En transit » |
| `Customer Information Manifested` | DAI Post, le partenaire pour la Nouvelle-Zélande, a reçu les données. | aucun recul |
| `Checked in at origin hub`, `Airline Delay`, `Collected from the airline for processing` | Chez DAI Post aux États-Unis, retard de vol (`Airline Delay` signale un vol retardé, pas un problème sur le colis), puis prise en charge à l'arrivée. | « En transit » |
| `Consignment received at the PostNL Acceptance Centre`, `The item has been manifested and ready to transport`, `The item is tied to a MAWB and ready for shipment`, `The item is received by PostNL`, `The item is on transport to the country of destination` | [PostNL](carrier:postnl), via Spring, a pris le colis en charge et l'achemine vers le pays de destination. | « En transit » |
| `The consignment has arrived in the country of destination`, `The item has arrived in the country of destination`, `The item is at the PostNL sorting center`, `The item is processed at the PostNL sorting center`, `The item has been processed in the country of destination` | Arrivée dans le pays de destination et tri dans le réseau PostNL. | « En transit » |
| `The item is at customs`, `The item is being processed by customs` | Contrôle douanier à l'importation sur un envoi suivi par PostNL. | « À la douane » |
| `Item released by customs` | Dédouanement terminé chez PostNL ; le colis repart. | « En transit » |
| `The item is out for delivery` | Tournée de livraison annoncée par PostNL. | « En livraison » |
| `The item will be scheduled for a new delivery attempt` | Échec de remise signalé par PostNL ; un nouveau passage sera programmé. | « Tentative de livraison » |
| `Your item has been dropped off by the sender with our postal partner in the country of origin.`, `Your item is ready to be forwarded from its foreign territory. It will be given to the carrier for forwarding.` | Mentions de [La Poste](carrier:la-poste) relayées sur un colis acheminé vers la France via PostNL, avant son arrivée en France. | « En transit » |
| `The parcel has left the parcel center.`, `The parcel has reached the parcel center.` | Réseau européen de GLS : départ du hub puis arrivée dans un centre de tri. | « En transit » |
| `The parcel is expected to be delivered during the day.` | Réseau européen de GLS : colis en tournée aujourd'hui. | « En livraison » |
| `The parcel has been delivered.` | Réseau européen de GLS : colis livré. | « Livré » |
| `Departed Hub`, `Shipment Received in Depot`, `Shipment Departed Depot` | Partenaire britannique de [Royal Mail](carrier:royal-mail) : en route vers le Royaume-Uni ou passage en dépôt. | « En transit » |
| `Arrived in customs` | Même partenaire britannique : entrée en douane au Royaume-Uni. | « À la douane » |
| `Ready for Collection` | Même partenaire britannique : colis disponible en point de retrait. | « Prêt à être retiré » |
| `Duty &/or tax fees requested` | Le transporteur final demande le paiement de droits ou taxes avant de livrer. | « À la douane » |
| `Received payment` | Ces frais ont été réglés ; l'acheminement reprend. | « En transit » |
| `The item is available at the pickuppoint` | Le colis t'attend dans un point de retrait du transporteur local. | « Prêt à être retiré » |

Une prise en charge partenaire après `Parcel Shipped` compte comme « En transit » et non comme un retour au départ. Peek classe les autres formulations partenaires avec ses règles habituelles ; une phrase inconnue reste affichée telle quelle sans reculer un colis déjà en tournée ou livré.

## Qui livre les colis ePost Global en France, en Belgique et en Suisse

ePost Global s'appuie sur des partenaires postaux et des transporteurs du dernier kilomètre sur six continents. Dans les historiques connus de Peek figurent notamment [PostNL](carrier:postnl) via Spring (avec reprise par [La Poste](carrier:la-poste) vers la **France**), le réseau européen de GLS, OSM Worldwide, ECMS Express, DAI Post et [Royal Mail](carrier:royal-mail). Lorsqu'ePost Global ne nomme pas de partenaire mais fournit un numéro postal international valide (deux lettres, neuf chiffres, deux lettres), Peek interroge la poste du pays de destination : [La Poste](carrier:la-poste) en **France**, [bpost](carrier:bpost) en **Belgique** et [La Poste suisse](carrier:swiss-post) en **Suisse** (ou [Postes Canada](carrier:canada-post) au Canada).

**Délais annoncés et suivi selon le service.** Depuis les États-Unis, la page *Cross-Border Parcel Shipping* d'ePost Global annonce à titre indicatif `1-5 days` porte-à-porte sur la plupart des axes pour *Expedited Parcel with Delivery Confirmation*, et `4 to 8 days` vers le Canada ou `5 to 10 days` vers l'Europe de l'Ouest pour *Priority Parcel with Delivery Confirmation* (`4 to 8 days` vers le Canada et l'Europe de l'Ouest pour *Priority Packet Tracked*, avec suivi porte-à-porte vers 48 pays clés). Cinq des sept produits colis incluent un suivi porte-à-porte ; les deux formules économiques fournissent un suivi dans le pays d'arrivée une fois le colis entré sur place. Les tentatives de remise et le délai de garde suivent les règles du transporteur final : vois [livraison manquée](guide:missed-delivery) et [colis non réclamé](guide:uncollected-parcel).

**Douane dans l'UE, en France, en Belgique et en Suisse.** Dans l'**Union européenne** (**France** et **Belgique**), depuis le 1er juillet 2026, les biens achetés en ligne hors UE dans des envois d'une valeur intrinsèque maximale de 150 € supportent un droit de douane de 3 € par article ; depuis le 1er novembre 2026 s'ajoutent des frais de traitement de l'Union de 2 € par article, quelle que soit la valeur (un article regroupe les biens d'un même colis partageant classement tarifaire, désignation et, si requise, origine ; au-delà de 150 €, tarif douanier normal). Selon la douane française, en **France métropolitaine**, la TVA s'applique « dès le premier euro » (sauf si elle a déjà été facturée lors de l'achat via le guichet IOSS) ; en **Guadeloupe**, **Martinique** et **La Réunion**, elle s'applique si la valeur totale « dépasse 22 euros », et elle « n'est provisoirement pas applicable dans les départements de la Guyane et de Mayotte » (hors octroi de mer et droits au-delà de 150 €). En **Suisse**, selon l'OFDF, la TVA est de 8,1 % (2,6 % au taux réduit), mais « les droits de douane inférieurs à 5 francs ne sont pas perçus » et « les montants d'impôt inférieurs à 5 francs ne sont pas perçus », hors frais éventuels du transporteur. La note douanière d'ePost Global précise que les frais de 2 € visent aussi bien les envois `DDP` (droits et frais encaissés à la commande) que `DDU` (à régler par le destinataire à la livraison, faute de quoi le colis peut être retenu). Consulte [colis bloqué en douane](guide:customs).

## Colis ePost Global bloqué, en retard ou introuvable

**« En attente de ePost Global ».** Quand `epgtrack.com` répond `We haven't received any tracking updates`, Peek affiche « En attente de ePost Global » et continue de vérifier. Vérifie que le code compte bien `EPG` et 10 à 15 chiffres ; si ePost Global ne connaît toujours pas ce numéro après deux jours et qu'aucun service de suivi ne le trouve non plus, Peek affiche : « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. »

**Plus rien après `In transit to …`.** Le partenaire a pris le relais et son propre suivi est souvent plus détaillé. Si Peek n'y a pas encore basculé automatiquement, copie le numéro inscrit après `tracking #:` et ajoute-le dans Peek.

**Immobile plusieurs jours.** Au bout de quatre jours sans nouveau scan, Peek signale le jour du dernier scan (« Aucune mise à jour depuis lun 12 oct »), rappelle qu'un colis reste souvent silencieux sur un long vol, en douane ou le week-end (après trois semaines sans rien de nouveau, il conseille plutôt de contacter le vendeur), et affiche le bouton « Demande de l’aide à ePost Global » (ou celui du partenaire dès que Peek suit le colis chez un transporteur dont il connaît la page d'aide ; OSM Worldwide et DAI Post n'en ayant pas dans le catalogue, le lien reste celui d'ePost Global). Consulte aussi `epostglobalshipping.com/service-updates` pour les suspensions de ligne éventuelles et [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

**Indiqué livré mais rien reçu, perdu ou retourné.** Interroge d'abord le transporteur local muni de son numéro, puis préviens la boutique qui a expédié la commande : c'est elle qui détient le contrat avec ePost Global pour un remboursement ou un renvoi. Vois [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact ePost Global

Sur `epostglobalshipping.com` :

- **Téléphone (États-Unis)** : `+1 866-784-8444` (numéro gratuit américain) et `+1 310-784-8485` (ligne locale), sans horaires ni adresse e-mail de support publiés sur la page.
- **Assistant en ligne ePost Assist** : accessible sur `epostassist.epgtrack.com` depuis le bouton *Track a package* ou *Chat with our online support*, avec les options *Check package status*, *Create a support ticket* et *Get support ticket status*.
- **Formulaire d'assistance** : sur `epostglobalshipping.com/contact-support` (*Looking for support beyond tracking a package?*), avec champ facultatif pour le numéro de suivi.
- **Siège social** : `11137 Warland Drive, Cypress, CA 90630, USA`.

**Attention aux SMS frauduleux.** Les adresses officielles d'ePost Global utilisées pour le suivi et l'aide sont `epgtrack.com`, `epostassist.epgtrack.com`, `ppxtrack.com` et `epostglobalshipping.com`. Sur un envoi `DDU` vers la France, la Belgique ou la Suisse, toute demande légitime de droits ou de TVA provient de la poste ou du transporteur qui y livre : vérifie toujours l'adresse avant de payer, et consulte [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

## Suivre un colis ePost Global avec Peek

[Peek](/) identifie aussitôt un numéro composé de `EPG` et 10 à 15 chiffres (« Transporteur détecté ») et interroge `epgtrack.com` sans compte ni code postal. Tu peux aussi coller un lien de suivi `epgtrack.com` ou `ppxtrack.com` : Peek en extrait le numéro (le premier si `?id=` en contient plusieurs). Si tu n'as que le numéro du transporteur partenaire, colle-le quand même : Peek suit plus de 4 000 transporteurs.

Peek affiche le statut, chaque étape avec son lieu, le pays de destination ainsi que le partenaire et son numéro lorsqu'ePost Global les communique. Il écarte la barre de progression, les codes postaux, les numéros de dépôt ou de rue, la référence client et les numéros de lettre de transport aérien (`MAWB`). Comme `epgtrack.com` ne fournit ni date de livraison prévue, ni poids, ni preuve de livraison, Peek n'en affiche pas. Le lien « Ouvrir le site ePost Global » ouvre directement ton numéro sur `epgtrack.com`.

Le traceur publie ses horaires sans fuseau. Peek lit `Parcel Received and Processing` et `Parcel Shipped` sur l'horloge de l'État américain du centre lorsque cet État n'a qu'un seul fuseau, les lignes PostNL sans pays sur l'horloge de PostNL, celles du réseau européen de GLS sur l'heure d'Europe centrale et celles du partenaire britannique de Royal Mail sur l'heure du Royaume-Uni ; les autres lignes sont datées d'après leur lieu ou l'étape du trajet. Tout s'affiche dans le fuseau de ton appareil et, sur `peektracker.com` comme dans les applications iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : … ».

Quand ePost Global désigne un partenaire que Peek connaît, la fiche affiche « Livraison par PostNL » (par exemple), ou « Annoncé à PostNL, pas encore reçu » tant que ce partenaire répertorie le colis sans scan propre, et indique le numéro partenaire après le code `EPG`. Peek interroge alors ce partenaire (sous son numéro, ou sous le numéro `EPG` s'il n'en donne pas) ; sans partenaire nommé, Peek interroge la poste du pays de destination pour un numéro postal valide, ou l'unique transporteur désigné par le format du second numéro (parmi les transporteurs dont Peek lit le suivi direct sans donnée supplémentaire). Dès que ce transporteur présente un scan au moins aussi récent que le dernier d'ePost Global, Peek bascule dessus tout en conservant l'historique d'ePost Global (ePost Global ne précisant pas quelle société GLS nationale livre, Peek reste sur les lignes du réseau européen de GLS relayées par ePost Global).

Tant que tu gardes le colis ouvert ou, le jour de sa livraison, avec les notifications activées, Peek vérifie les mises à jour jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h locales le jour où il passe en « En livraison » tant que le traceur d'ePost Global répond (après un relais vers le transporteur final, Peek suit le rythme de ce dernier ; via les services de suivi seuls : toutes les heures, toutes les 3 heures après un jour calme et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont actives) ; sinon, et après deux jours sans nouveauté (12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Notifications au choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni ePost Global ni le vendeur pour toi.

## Questions fréquentes sur le suivi ePost Global

### Qu'est-ce que PPX Track ?

C'est un autre portail de suivi d'ePost Global (`ppxtrack.com`, limité à 10 numéros par recherche) et un alias d'ePost Global dans Peek. Peek lit les liens `ppxtrack.com` exactement comme ceux de `epgtrack.com` et en extrait le numéro.

### Que signifie « In transit to » sur le suivi ePost Global ?

ePost Global a remis ton colis au partenaire chargé de la suite du transport. La ligne indique le service utilisé et, après `tracking #:`, le numéro de suivi propre à ce partenaire.

### Pourquoi mon suivi ePost Global reste-t-il bloqué sur « Data Received » ?

La boutique a envoyé les informations électroniques à ePost Global, mais aucun centre de tri américain n'a encore scanné physiquement le paquet. Demande au vendeur à quelle date il a remis ta commande au transporteur.

### Qui livre les colis ePost Global en France, en Belgique et en Suisse ?

Un transporteur partenaire désigné à la remise : dans les dossiers suivis par Peek, on retrouve notamment PostNL via Spring avec reprise par La Poste vers la France et le réseau européen de GLS. Lorsqu'un numéro postal international est attribué sans partenaire nommé, Peek interroge la poste nationale (La Poste en France, bpost en Belgique, La Poste suisse en Suisse).

### Comment contacter ePost Global ?

Adresse-toi d'abord à la boutique qui a expédié ta commande. Pour joindre directement ePost Global, passe par l'assistant *ePost Assist* sur `epostassist.epgtrack.com`, par le formulaire `epostglobalshipping.com/contact-support` ou par téléphone aux États-Unis au `+1 866-784-8444` ou `+1 310-784-8485`.

:::sources
- [ePost Global : page d'accueil](https://epostglobalshipping.com/) – consolidateur international, 20M+ d'envois par an, 100+ transporteurs, 200+ pays, adresse à Cypress et numéros de téléphone
- [ePost Global : About us](https://epostglobalshipping.com/about-us) – plus de 25 ans d'activité, origines chez R.R. Donnelley (XPO), partenaires sur six continents
- [ePost Global : Cross-Border Parcel Shipping](https://epostglobalshipping.com/cross-border-parcel-shipping) – traitement en 24 h ou moins, délais indicatifs vers le Canada et l'Europe de l'Ouest, suivi porte-à-porte vs dans le pays, DDP et DDU, portails PPX et USPS
- [ePost Global : eDGE](https://epostglobalshipping.com/edge) – réseau domestique États-Unis et Canada avec plus de 100 transporteurs
- [ePost Global : EU customs fee ecommerce](https://epostglobalshipping.com/eu-customs-fee-ecommerce) – frais de 2 € par article en DDP et DDU, droit de 3 € jusqu'à 150 €, IOSS et retenue jusqu'au paiement en DDU
- [ePost Global : Contact support](https://epostglobalshipping.com/contact-support) – formulaire d'assistance, lien vers ePost Assist, numéros de téléphone et adresse
- [ePost Global : Contact us](https://epostglobalshipping.com/contact-us) – lien d'aide en ligne pour un colis manquant
- [ePost Global : Service updates](https://epostglobalshipping.com/service-updates) – suspensions et perturbations de service par destination
- [EPG Track](https://epgtrack.com/) – traceur officiel ePost Global jusqu'à 20 numéros
- [PPX Track](https://ppxtrack.com/) – portail PPX Track jusqu'à 10 numéros
- [ePost Assist](https://epostassist.epgtrack.com/) – suivi de colis et création ou consultation d'un ticket de support
- [Commission européenne : Réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € depuis le 1er juillet 2026 et frais de traitement de 2 € depuis le 1er novembre 2026
- [Commission européenne : Orientations sur le droit de 3 € et les frais de l'Union (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € quelle que soit la valeur, définition d'un article et exonération TVA via IOSS
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA en France métropolitaine et règles en Guadeloupe, Martinique, La Réunion, Guyane et Mayotte
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse de 8,1 % / 2,6 % et non-perception sous 5 francs
- [Universal Parcel Scraper : ePost Global](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/epost-global) – alias, format EPG, liens, statuts propres et partenaires, horloges, champs écartés et page d'aide
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats Colis Privé, Purolator et USPS, relais vers le partenaire ou la poste du pays de destination
:::
