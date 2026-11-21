---
title: Suivi PostNL : numéros 3S et NL, statuts et livraison
description: Suivi PostNL hors des Pays-Bas : format des numéros NL et 3S, sens des statuts sur postnl.post, qui livre le colis dans ton pays et qui contacter s'il bloque.
slug: postnl-suivi
published: 2026-11-17
updated: 2026-11-21
---

PostNL est l’opérateur postal néerlandais. Il achemine à l’étranger les colis et courriers des boutiques néerlandaises et, via sa filiale internationale Spring GDS, les commandes de vendeurs en ligne du monde entier. Dans les deux cas, tu peux suivre l’envoi sur le site international de PostNL, [postnl.post](https://postnl.post/) (en anglais), ou dans le champ ci-dessus. Une fois arrivé dans ton pays, le colis passe souvent au service postal local ou à un transporteur partenaire. Le numéro figure dans l’e-mail d’expédition ou sur ta page de commande ; sinon, lis [retrouver un numéro de suivi](guide:find-tracking-number).

## Numéro de suivi PostNL : à quoi il ressemble

Selon sa documentation destinée aux marchands, PostNL utilise deux familles de codes-barres (exemples inventés) :

| Numéro (inventé) | Ce qu’il désigne |
| --- | --- |
| `CW482910576NL` | Deux lettres, 9 chiffres, puis `NL` : numéro postal international S10 de PostNL. Un code commençant par `C` (comme `CW`, `CC`, `CD` ou `CP`) correspond à un colis *Parcels Non-EU* hors de l’UE, par exemple vers la Suisse, le Canada ou Andorre. |
| `LA482910576NL` | `L` : petit paquet ou pli suivi (*Boxable* ou *Packet Track & Trace*). `LW` désigne le même service. |
| `RI482910576NL` | `R` : envoi recommandé (*Registered Letter*). `RF` désigne le même service. |
| `UL482910576NL` | `U` : petit paquet non suivi (*Untracked*, aussi `UE` ou `UM`). Ne compte pas sur des scans à destination. |
| `3SABCD482910576` | `3S`, un code client en lettres (1 à 4 lettres), puis des chiffres sur 15 caractères au total : code-barres colis PostNL. |
| `3SABCD4829105` | Même code-barres `3S` en 13 caractères. PostNL l’utilise notamment pour *Parcel EU*, son offre colis des Pays-Bas vers l’UE (dont la France, Monaco, la Corse et le Luxembourg ; hors Andorre). |

Le neuvième chiffre d’un numéro `NL` est une clé de contrôle S10 : une seule erreur de frappe suffit généralement à rendre le numéro invalide.

Le portail postnl.post accepte jusqu’à 150 numéros à la fois. Il répond pour les numéros `NL` et les codes `3S` de 15 caractères, mais rejette les `3S` de 13 caractères avec `The shipment barcode was incorrect.`. Pour un envoi *Parcel EU* de 13 caractères, demande à la boutique le code-barres du partenaire (*DownPartnerBarcode*) : lorsque le vendeur crée l’étiquette *Parcel EU*, PostNL lui renvoie aussi le numéro du partenaire étranger chargé de la livraison finale, utilisable chez ce partenaire. Tant que le colis se trouve encore aux Pays-Bas, ce numéro partenaire peut toutefois changer.

## Statuts de suivi PostNL expliqués

Sur postnl.post, une barre de progression affiche `Pre-advised`, `Preparing`, `Departed`, `Transit`, `Arrived`, `Customs`, `Processing` et `Delivered`, suivie des scans en anglais. La plupart des étapes indiquent le pays où elles ont eu lieu. Voici les mentions courantes dans l’ordre habituel :

| postnl.post affiche | Ce que cela signifie pour toi |
| --- | --- |
| `Pre-advised`, `Preparing` | L’expéditeur a enregistré l’envoi et transmis les données à PostNL, qui n’a peut-être pas encore le colis. |
| `Consignment received at the PostNL Acceptance Centre`, `The shipment is handed over in bulk final acceptance of the item to be confirmed` | PostNL a réceptionné le lot du vendeur ; le scan individuel de ton colis peut encore suivre. |
| `The item is received by PostNL` | PostNL a scanné ton article. Cet événement peut arriver plusieurs jours après l’entrée du lot. |
| `Departed`, `Transit` | En transit entre deux pays, souvent par avion. |
| `Arrived` | Arrivé dans un pays du parcours, en général le pays de destination. |
| `Customs` | En cours de dédouanement dans le pays d’arrivée. |
| `The item has arrived at the domestic sorting centre`, `The item is at the local sorting centre` | En cours de tri dans le pays mentionné à côté du scan. |
| `The item is at the local delivery office` | Arrivé au centre local qui assurera la tournée. |
| `The item is out for delivery` | En cours de livraison vers ton adresse. |
| `Driver is en route to the pickup location` | Le chauffeur achemine le colis vers un point de retrait, pas à ta porte. |
| `Pick-up point` | Disponible pour retrait dans un point relais ou bureau. |
| `Unsuccesfull`, `Undelivered` | Tentative de livraison échouée (la faute d’orthographe vient de PostNL). |
| `Undeliverable item, has been returned to shipper` | Colis non distribuable, renvoyé à l’expéditeur. |
| `The item has been returned and arrived at PostNL` | Le retour est revenu chez PostNL aux Pays-Bas. |
| `Delivered` | Livré. |

`The shipment barcode was not found.` indique que postnl.post n’a encore aucune donnée pour ce code ou qu’un caractère est erroné. La mention « Destination: unknown » signifie qu’aucun scan n’a encore nommé le pays d’arrivée. Si tu renseignes dans Peek le code postal de livraison d’un colis `3S`, Peek interroge aussi le suivi propre de PostNL, dont les intitulés diffèrent pour un colis livré à l’étranger : `Your parcel has crossed the border`, `Your parcel's at a sorting centre in the destination country`, `Your shipment is on its way to the recipient` (en livraison) et `We missed you; we’ll try again` (passage infructueux).

## Qui livre les colis PostNL dans ton pays ?

PostNL ou Spring GDS assure le transport jusqu’à ton pays. Spring GDS explique gérer le trajet international tandis qu’un partenaire local assure la livraison finale, et précise qu’à l’entrée dans ton pays le colis est souvent remis au service postal local (Spring GDS cite notamment USPS, Royal Mail et Correos).

- **Numéro `NL` (format S10) :** ce format de l’Union postale universelle (UPU) est partagé entre opérateurs postaux. Une fois le colis arrivé dans ton pays, tu peux essayer le même numéro chez ton opérateur postal national, comme [La Poste](carrier:la-poste) en France, [bpost](carrier:bpost) en Belgique, [La Poste suisse](carrier:swiss-post) en Suisse, [POST Luxembourg](carrier:post-luxembourg) au Luxembourg ou [Postes Canada](carrier:canada-post) au Canada (pour un colis venu de l’étranger absent de son outil de suivi, Postes Canada invite à faire ouvrir une enquête par l’expéditeur auprès de sa poste d’origine). Un petit paquet non suivi (`UL`) peut ne rien afficher à destination.
- **Code `3S` (*Parcel EU*) :** en France (Monaco et Corse inclus) et au Luxembourg, le colis est livré par le partenaire de PostNL à l’étranger, que la documentation de PostNL ne nomme pas. En Belgique, la documentation *Dutch domestic* de PostNL prévoit aussi ses propres envois Pays-Bas–Belgique (*Belgium Standard delivery* et retrait dans un point PostNL en Belgique).
- **Envois hors UE (*Parcels Non-EU*) :** vers la Suisse, le Canada ou Andorre, PostNL utilise un code-barres S10 commençant par `C` (`CW…NL`).

Les avis de passage, nouvelles présentations et délais de garde suivent les règles du transporteur qui livre localement ; consulte [livraison manquée](guide:missed-delivery) et [colis non récupéré](guide:uncollected-parcel). Pour la TVA, les droits d’importation et les frais de dossier hors UE, lis [colis bloqué en douane](guide:customs) : Spring GDS rappelle que les droits et taxes de douane sont des taxes publiques et que le déclarant peut demander une facture ou une pièce d’identité par e-mail ou SMS.

## Colis PostNL bloqué, perdu ou retourné

**Aucun scan pour l’instant.** Avec `Pre-advised` ou `Preparing`, le vendeur a réservé l’expédition mais n’a peut-être pas encore remis le colis. Spring GDS conseille d’attendre 24 à 48 heures après réception du numéro pour voir apparaître le premier scan.

**Immobile en transit.** Après `Departed` ou `Transit`, un envoi voyageant par avion ou par bateau ne reçoit aucun scan avant son arrivée, et le passage en douane (`Customs`) dure souvent plusieurs jours. Essaie ensuite le numéro `NL` chez ta poste nationale. Après quatre jours sans nouveau scan aux étapes « Étiquette créée », « Pris en charge » ou « En transit », Peek indique dans ta liste le jour du dernier scan (« Aucune mise à jour depuis lun 12 oct ») et, sur la fiche du colis : « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger PostNL. », avec le lien « Demande de l’aide à PostNL ». Au bout de trois semaines sans scan, la fiche dit plutôt : « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger PostNL. » Plus de détails dans [suivi qui ne bouge plus](guide:tracking-not-updating).

**Marqué livré mais absent.** Spring GDS recommande d’interroger tes voisins, de vérifier les endroits abrités (porche, garage) et l’accueil ou le gardien, et note qu’un transporteur marque parfois un colis `Delivered` jusqu’à 24 heures avant sa remise effective. Si tu ne trouves toujours rien, contacte le vendeur et lis [colis livré mais non reçu](guide:delivered-not-received).

**Perdu ou abîmé.** Préviens aussitôt le vendeur et photographie tout emballage endommagé : selon Spring GDS, le marchand a besoin de ces photos pour déposer une réclamation auprès d’eux. Voir [colis perdu](guide:lost-parcel).

**Retourné à l’expéditeur.** Demande au vendeur un nouvel envoi ou un remboursement. Spring GDS cite parmi les causes fréquentes une adresse erronée (*Incorrect Address*) et un colis non retiré à temps (*Unclaimed*), et précise qu’un retour déjà engagé ne peut plus être annulé. Voir [colis retourné à l’expéditeur](guide:returned-to-sender).

## Contacter PostNL et le service client

Selon les pages officielles consultées :

- **postnl.post :** pour toute livraison hors des Pays-Bas, le site renvoie vers la boutique en ligne (*« Questions about your delivery outside The Netherlands? Please contact your webshop. »*). Pour une livraison aux Pays-Bas, il renvoie au service client de PostNL.
- **Spring GDS :** en raison de la protection des données et de ses contrats avec les marchands, Spring GDS n’échange pas sur le détail d’un envoi avec les destinataires. C’est le vendeur, titulaire du compte, qui ouvre une enquête auprès de Spring GDS.
- **Transporteur local :** pour une livraison manquée ou une nouvelle date de passage, contacte directement l’opérateur qui livre chez toi ; Spring GDS ne peut pas reprogrammer les tournées locales.

Dans tous les autres cas, le vendeur ou la place de marché où tu as passé commande reste l’interlocuteur le plus rapide.

## Suivre un colis PostNL avec Peek

[Peek](/) reconnaît immédiatement les numéros `NL` dotés d’une clé S10 valide ainsi que les codes `3S` comportant un code client de quatre lettres. Un code `3S` à trois lettres est attribué à [DHL eCommerce Netherlands](carrier:dhl-ecommerce-nl), même si les directives de PostNL prévoient aussi des plages à trois lettres. Avec une ou deux lettres, la forme ne suffit pas à trancher (avec deux lettres, Peek interroge aussi DHL eCommerce Netherlands) : colle-le quand même, Peek suit plus de 4 000 transporteurs. Pendant la recherche s’affiche « Vérification des services de suivi… » ; si un autre réseau est retenu, touche « Changer » et choisis PostNL. Les liens de postnl.post (`/track?barcodes=` ou `/details/…`) et de mailingtechnology.com (`/tracking?tn=`), le traceur de Spring GDS, sont également reconnus comme PostNL. Pour un code `3S`, le formulaire propose le champ facultatif « Code postal de livraison » : « PostNL n’affiche un colis envoyé à l’intérieur des Pays-Bas qu’avec le code postal de livraison. » Avec ce code (et « Pays de livraison » hors des Pays-Bas), Peek interroge d’abord le suivi propre de PostNL ; sans lui, d’autres sources prennent le relais.

Peek lit postnl.post, ainsi que le suivi propre de PostNL quand le code postal est fourni. Il affiche le statut, l’historique avec le pays de chaque scan, l’heure de livraison et le pays de destination. Les heures sont converties dans le fuseau horaire de ton appareil (au bas de l’historique figure « Heures dans ton fuseau horaire : … ») ; dans les pays qui comptent plusieurs fuseaux horaires, comme le Canada, un scan qui ne nomme que le pays doit en supposer un seul et peut donc être décalé de quelques heures. PostNL ne fournit ni date de livraison prévue ni nom de l’expéditeur. Pour un numéro `NL`, dès que PostNL indique le pays de destination (ou, à défaut, d’après ton pays — la région de ton téléphone dans l’app ou le pays de connexion dans le navigateur), Peek interroge la poste nationale correspondante sous le même numéro — [La Poste](carrier:la-poste) en France, [bpost](carrier:bpost) en Belgique, [La Poste suisse](carrier:swiss-post) en Suisse, [POST Luxembourg](carrier:post-luxembourg) au Luxembourg ou [Postes Canada](carrier:canada-post) au Canada — et bascule vers elle dès qu’elle publie une avancée plus récente (« Livraison par … »). Comme postnl.post rejette les `3S` de 13 caractères, sans code postal Peek les cherche via des [services de suivi universels](guide:universal-tracker).

Peek vérifie PostNL jusqu’à toutes les 30 minutes, y compris quand le colis est en livraison, mais un rafraîchissement manuel peut vérifier plus tôt. Lorsqu’une poste nationale prend le relais pour la livraison, Peek adopte la cadence de ce transporteur. Tu peux activer les alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni PostNL ni le vendeur à ta place.

## Questions fréquentes sur le suivi PostNL

### Où se trouve mon colis PostNL ?

Le dernier scan indique l’étape actuelle et le pays où se trouve l’envoi. Dès qu’un numéro `NL` apparaît arrivé dans ton pays, essaie ce même numéro sur le site de ton opérateur postal national, qui détaille généralement les étapes locales.

### Que signifie « The shipment barcode was incorrect » sur PostNL ?

Cela veut dire que postnl.post n’accepte pas ce code-barres. Il s’agit le plus souvent d’un code `3S` de 13 caractères utilisé par l’offre *Parcel EU* de PostNL : demande au vendeur le code-barres du partenaire local chargé de la livraison finale et suis ce numéro chez lui.

### Qui livre les colis PostNL en France, en Belgique ou en Suisse ?

Spring GDS, la branche internationale de PostNL, indique qu’une fois entré dans le pays de destination, un colis est souvent remis au service postal local. Un numéro postal `NL` (norme S10) peut ainsi être essayé chez La Poste, bpost ou La Poste suisse après son arrivée. Un colis *Parcel EU* à code `3S` de 13 caractères vers la France est remis au partenaire étranger de PostNL (non nommé dans sa documentation), tandis que PostNL propose aussi ses propres livraisons et points de retrait en Belgique.

:::sources
- [PostNL : International Tracking](https://postnl.post/) – limite de 150 numéros, barre d’étapes, réponses « not found » et « incorrect », destination et renvoi vers la boutique
- [PostNL Developer : Barcode guidelines](https://developer.postnl.nl/docs/#/http/api-endpoints/send-track/barcode/guidelines) – codes 3S de 13 ou 15 caractères, code client de 1 à 4 lettres, numéros S10 terminés par NL
- [PostNL Developer : Product codes International](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-international) – Parcel EU en 3S de 13 caractères (France avec Monaco et Corse, hors Andorre ; Luxembourg), code-barres partenaire, Parcels Non-EU en C/CW, LA/LW, RI/RF et UL/UE/UM
- [PostNL Developer : Product codes Dutch domestic](https://developer.postnl.nl/docs/#/http/reference-data/product-codes-dutch-domestic) – envois Pays-Bas vers Belgique et points PostNL en Belgique
- [Spring GDS : We are Spring GDS](https://www.spring-gds.com/about-us/) – filiale internationale du groupe PostNL spécialisée dans l’e-commerce transfrontalier
- [Spring GDS : Track and Trace](https://www.spring-gds.com/track/) – remise au service postal local, partenaire local, délai de 24–48 h avant le premier scan, pauses en transit et en douane, livré mais non reçu, photos de dommage, retours et absence de contact direct avec les consommateurs
- [Spring GDS : Contact us](https://www.spring-gds.com/contact-us/) – questions de suivi renvoyées vers la boutique en ligne
- [Postes Canada : Repérer les colis internationaux](https://www.canadapost-postescanada.ca/cpc/en/support/kb/tracking/tracking-international-packages.page) – enquête ouverte par l’expéditeur auprès de la poste d’origine
- [UPU : Norme S10, Identification of postal items (PDF)](https://www.upu.int/UPU/media/upu/files/postalSolutions/programmesAndServices/standards/S10-12.pdf) – identifiant à 13 caractères partagé entre opérateurs postaux et chiffre de contrôle
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – reconnaissance des formats, code postal 3S, bascule vers les postes nationales et cadence de 30 minutes dans Peek
:::
