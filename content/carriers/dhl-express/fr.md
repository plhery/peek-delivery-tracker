---
title: DHL Express suivi : numéro de bordereau et statuts
description: Suivre un colis DHL Express : où trouver le numéro de bordereau à 10 chiffres, le sens des statuts, la douane, les livraisons manquées et le contact.
slug: dhl-express-suivi
published: 2026-10-13
updated: 2026-10-27
---

Colle un numéro de bordereau DHL Express, ou un lien de suivi MyDHL+, dans le champ ci-dessus pour suivre ton colis. DHL propose aussi son propre suivi, sur son site (MyDHL+) et dans l'application DHL Express.

## Numéro de suivi DHL Express : le bordereau à 10 chiffres

En français, DHL parle de « numéro de bordereau » ou de « lettre de transport » (le « waybill number » des pages en anglais). Selon DHL, c'est l'expéditeur ou la boutique qui te le fournit, le plus souvent dans l'e-mail de confirmation de commande ou d'expédition ; sinon, demande-le-lui. Les messages On Demand Delivery de DHL arrivent par SMS ou par e-mail, avec un lien. Plus de pistes dans [où trouver son numéro de suivi](guide:find-tracking-number).

Un lien de suivi MyDHL+ contient aussi le numéro, après `id=`.

| Forme (chiffres inventés) | Ce que c'est |
| --- | --- |
| `1234567891` | Le bordereau : 10 chiffres, le numéro qui suit tout l'envoi. Le dernier chiffre est une clé de contrôle. |
| `JD014600001234567890` | L'identifiant de pièce : `JD01` puis 16 chiffres, imprimé `(J)JD…` sous le code-barres du bas de l'étiquette. Chaque colis de l'envoi a le sien, qui renvoie au bordereau. |

[Mondial Relay](carrier:mondial-relay), [Relais Colis](carrier:relais-colis), TIPSA, Estafeta, Packeta, DHL eCommerce Iberia et [DHL Paket](carrier:dhl) utilisent aussi des numéros à 10 chiffres : regarde quel transporteur nomme l'e-mail de la boutique.

DHL réutilise ses numéros de bordereau : un numéro peut ramener les scans d'un envoi plus ancien, que Peek ignore.

## DHL Express suivi colis : ce que veulent dire les statuts

Voici les libellés de scan que Peek lit chez DHL Express, dans l'anglais de DHL, à peu près dans l'ordre où un colis les rencontre, avec le statut que Peek affiche pour chacun. Beaucoup se terminent par le site concerné, par exemple `Processed at` suivi d'une ville et d'un pays.

| Scan de DHL Express | Peek affiche | Ce que ça veut dire |
| --- | --- | --- |
| `Shipment information received`, `Supplementary information has been received and merged with applicable shipment data` | Étiquette créée | L'expéditeur a transmis à DHL les données de l'envoi. |
| `Shipment picked up`, `Shipment accepted` | Pris en charge | DHL Express a le colis. |
| `Processed at`, `Arrived at DHL Sort Facility`, `Shipment has departed from a DHL facility`, `Shipment is in transit to destination` | En transit | Il avance dans le réseau de DHL. |
| `Scheduled to depart on the next planned movement` | En transit | Il attend dans un site son prochain camion ou vol. |
| `Customs clearance status updated. Note - The Customs clearance process` …, `Processed for clearance at`, `Clearance event` | À la douane | Il passe la douane (voir plus bas). |
| `Payment is received and recorded for shipment related fees` | À la douane | Les droits ou frais sont payés ; il reste en douane jusqu'à la fin du dédouanement. |
| `Clearance processing complete at` | En transit | La douane en a fini avec lui, il repart. |
| `Arrived at DHL Delivery Facility` | En transit | Il est arrivé au site DHL qui va le livrer. |
| `Shipment is out with courier for delivery` | En livraison | Un coursier te l'apporte. |
| `Delivery attempted but no response at Consignee address`, `Delivery attempt could not be completed` | Tentative de livraison | La livraison n'a pas pu se faire. Le « consignee », c'est toi, le destinataire. |
| `Shipment is scheduled for delivery` | En transit | Une livraison est prévue lors d'une tournée suivante, par exemple après une tentative manquée. |
| `Awaiting collection by the consignee` | Prêt à être retiré | Il t'attend dans un site DHL pour que tu viennes le chercher. |
| `Delivered` | Livré | Il est livré. |
| `Shipment is on hold` | Problème signalé | DHL a arrêté l'envoi pour l'instant. |
| `Shipment arrived at incorrect facility. It will be sent to the correct destination` | Problème signalé | Il a été trié vers le mauvais site et repart vers le bon, avec du retard. |
| `Returned to shipper` | Retourné à l’expéditeur | Il repart chez l'expéditeur. |

## Livraison DHL Express manquée

Après une tentative manquée, le suivi affiche `Delivery attempted but no response at Consignee address` ou `Delivery attempt could not be completed`. Si `Shipment is scheduled for delivery` suit, une nouvelle tentative est prévue ; `Awaiting collection by the consignee` veut dire que le colis t'attend dans un site DHL pour que tu ailles le chercher. Pour modifier la livraison, passe par DHL Express, sur son site ou dans son application : Peek ne peut pas la modifier. Plus de pistes dans [livraison manquée](guide:missed-delivery) et [colis non retiré](guide:uncollected-parcel).

## Frais de douane sur un colis DHL Express

Un colis venu de l'extérieur de l'UE, ou de l'étranger pour la Suisse, peut être soumis à des frais d'importation. Tant qu'il est en douane, le suivi affiche `Customs clearance status updated…`, `Processed for clearance at` ou `Clearance event`, puis `Payment is received and recorded for shipment related fees` une fois les frais payés et `Clearance processing complete at` quand la douane le libère.

- **France et Belgique** : comme partout dans l'UE depuis le 1er juillet 2026, une commande de 150 € ou moins venue de l'extérieur de l'UE est soumise à un droit de douane forfaitaire de 3 € par article, en plus de la TVA ; c'est le vendeur ou le transporteur qui le perçoit. En France métropolitaine, la douane précise que ces taxes peuvent t'être demandées à la réception du colis par le transporteur, qui peut y ajouter ses propres frais de dédouanement, sans lien avec ce que perçoit la douane.
- **Suisse** : selon l'OFDF, c'est le transporteur qui dédouane les envois de courrier rapide, et lui seul peut t'aider à rechercher ton envoi.

Plus d'infos dans [colis bloqué en douane](guide:customs).

## Colis DHL Express bloqué, en retard ou perdu

Si le suivi ne bouge plus, ou affiche `Shipment is on hold`, lis [pourquoi un suivi de colis ne bouge plus](guide:tracking-not-updating).

Si tu as acheté dans une boutique, c'est à elle de te faire parvenir le colis, même quand DHL Express le transporte : dans l'UE, selon Your Europe, le commerçant répond des dommages subis par la marchandise de l'expédition jusqu'à la réception.

En France, selon Service-Public.fr, quand la livraison passe par le transporteur proposé par le vendeur, le vendeur est seul responsable : la perte ou l'avarie n'est à ta charge qu'une fois le colis physiquement entre tes mains. Sans date convenue, il doit livrer au plus tard 30 jours après la commande. Passé ce délai, demande-lui de livrer dans un délai supplémentaire raisonnable ; s'il ne livre toujours pas, tu peux annuler la commande, et il doit te rembourser sous 14 jours.

**Livré mais pas reçu ?** La page de suivi de DHL peut montrer la signature, que Peek ne garde pas. Vérifie sur le site de DHL, puis contacte la boutique. Plus de pistes dans [colis livré mais non reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel).

## DHL Express suivi téléphone : le contact en France

En France, DHL Express donnait ces contacts sur ses pages le 13 octobre 2026 :

- **France :** 09 69 32 93 93 pour les particuliers (« service gratuit + prix appel »), du lundi au vendredi de 8 h à 18 h et le samedi de 8 h 30 à 13 h 30. Service Douane au 01 79 51 74 25 et Service Droits et Taxes au 04 26 70 02 43 pour payer les droits et taxes d'une livraison en France (« Appel non-surtaxé, prix d'un appel local »). Chat 24 h/24 et WhatsApp sur la page [Aide et support](https://mydhl.express.dhl/fr/fr/help-and-support.html).

En Belgique et en Suisse, cherche les contacts de DHL Express sur le site de DHL de ton pays.

## Suivre un colis DHL Express avec Peek

[Peek](/) reconnaît un bordereau à 10 chiffres dont la clé de contrôle DHL Express est bonne : il place DHL Express en premier, l'interroge en même temps que d'autres transporteurs aux numéros de 10 chiffres, comme ACS Courier, Geniki Taxydromiki et DHL eCommerce Iberia, et retient celui qui connaît le colis. ACS et Geniki utilisent la même clé : si l'un d'eux connaît aussi le numéro, c'est toi qui choisis. Un numéro à 10 chiffres dont la clé est fausse n'est pas proposé à DHL Express : choisis-le avec « Changer de transporteur ». Les identifiants de pièce `JD01` et les étiquettes `JJD0146` (`JJD014612345678901234`) sont attribués tout de suite à DHL Express et ramenés à leur bordereau. Les liens de mydhl.express.dhl marchent aussi. Avec un lien dhl.com, colle plutôt le bordereau seul ; si un colis se retrouve sous « DHL », passe-le à « DHL Express » avec « Changer de transporteur ».

Peek lit DHL Express par le service de l'application mobile de DHL Express, sinon en ouvrant la page de suivi de dhl.com dans un navigateur sur son serveur, ce qui est plus lent et peut être refusé : des services de suivi prennent alors le relais. Il vérifie le suivi toutes les 10 minutes en journée, toutes les 2 à 5 minutes une fois le colis en livraison, toutes les une à deux heures la nuit, et peut t'envoyer des notifications : « Chaque scan », « Étapes importantes » ou « Livraison seulement ».

Tu vois le statut, chaque scan sur une carte avec son site, et le pays de destination. L'heure d'un scan est placée d'après la ville du site ou, à défaut, d'après les scans qui l'entourent. Les scans restent dans l'anglais de DHL, sauf `Delivered`, `Shipment picked up` et `Shipment information received`, que Peek écrit avec ses propres mots. Peek ne garde pas la signature, ignore les vieux scans d'un bordereau réutilisé et n'affiche ni date de livraison prévue, ni poids, ni Service Point. Il ne peut ni payer les droits ni modifier une livraison.

## Questions sur le suivi DHL Express

### Où trouver le numéro de bordereau DHL ?

Dans l'e-mail de confirmation de commande ou d'expédition de la boutique, en général. Sinon, demande-le à la boutique ou à l'expéditeur.

### DHL et DHL Express, c'est la même chose ?

Pas dans Peek : DHL Express y est un transporteur à part, distinct de [DHL Paket](carrier:dhl) et des transporteurs DHL eCommerce, chacun avec ses numéros et son suivi. Un colis sans bordereau à 10 chiffres ni identifiant `JD01` relève en général de l'un d'eux.

### Pourquoi mon numéro DHL Express n'est-il pas reconnu ?

Souvent, c'est le numéro d'un autre transporteur de DHL : un bordereau DHL Express a exactement 10 chiffres, et le dernier est une clé de contrôle, donc une faute de frappe la fait en général échouer. Un bordereau tout neuf peut n'afficher que `Shipment information received` tant que DHL n'a pas pris en charge le colis.

### Peut-on suivre un colis DHL Express sans numéro de bordereau ?

Pas dans Peek : il lui faut le bordereau à 10 chiffres ou un identifiant `JD01` de l'étiquette. Demande le bordereau à la boutique ou à l'expéditeur.

### Un SMS de DHL qui demande de payer, c'est une arnaque ?

Ça peut l'être. Cybermalveillance.gouv.fr signale de faux SMS et e-mails au nom de transporteurs, qui annoncent un problème de livraison ou des frais à payer et renvoient vers un faux site qui cherche à dérober tes données personnelles et ta carte bancaire. Ne clique pas sur le lien : vérifie le vrai suivi toi-même, sur le site de DHL ou dans Peek. En France, signale le SMS au 33700 (gratuit). Plus d'infos dans [arnaque colis par SMS](guide:delivery-scam-texts).

:::sources
- [DHL Express : FAQ – Suivi et Contrôle (France)](https://mydhl.express.dhl/fr/fr/help-and-support/faqs/tracking-monitoring.html) – numéro de bordereau
- [DHL Express : Aide et support (France)](https://mydhl.express.dhl/fr/fr/help-and-support.html#/contact_us) – Service Douane, Droits et Taxes, horaires, chat, WhatsApp
- [DHL Express : Guide des services et tarifs 2026 (France)](https://mydhl.express.dhl/content/dam/downloads/fr/fr/rate-guide/service_and_rate_guide_fr_fr_2026.pdf) – téléphones, messages On Demand Delivery
- [DHL : Service Client (France)](https://www.dhl.com/fr-fr/home/customer-service.html) – origine du numéro
- [Douane : Droit de douane forfaitaire de 3 euros sur les ventes à distance de biens importés](https://www.douane.gouv.fr/fiche/droit-de-douane-forfaitaire-de-3-euros-sur-les-ventes-distance-de-biens-importes) – 3 € par article jusqu'à 150 €, en vigueur depuis le 1er juillet 2026
- [Douane : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – taxes demandées à la réception par le transporteur, ses frais de dédouanement
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 3 € par article dans l'UE, perçu par le vendeur ou le transporteur, responsabilité du commerçant jusqu'à la réception
- [Service-Public.fr : Achat à distance : livraison du bien ou exécution de la prestation](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – vendeur seul responsable, transfert des risques, 30 jours, délai supplémentaire, annulation, remboursement sous 14 jours
- [OFDF : Frais de dédouanement et suivi des colis](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – dédouanement par le transporteur, recherche de l'envoi (Suisse)
- [Cybermalveillance.gouv.fr : L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – faux SMS au nom de transporteurs, vol de données et de carte bancaire, vérifier le vrai suivi, 33700
- [Universal Parcel Scraper : DHL Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dhl-express) – formes du bordereau et de l'identifiant de pièce, clé de contrôle, autres numéros à 10 chiffres, bordereaux réutilisés, scans de DHL Express et statut Peek de chacun, signature sur le site de DHL, ce que Peek lit chez DHL Express
:::
