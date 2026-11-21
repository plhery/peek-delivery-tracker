---
title: Suivi GlobalPost : numéros GP, statuts et livraison
description: Suis un colis GlobalPost : format des numéros GP et SDC, sens des étapes en anglais, services Economy à Plus, douane et suivi du transporteur final avec Peek.
slug: suivi-globalpost
published: 2026-11-21
updated: 2026-11-21
---

GlobalPost, filiale du groupe Auctane, est un service d'expédition internationale proposé aux vendeurs en ligne sur des plateformes comme ShipStation (aux États-Unis et au Royaume-Uni) et Stamps.com (plus de 220 pays desservis et plus de 60 millions d'envois livrés selon son site). Le colis passe d'abord par un centre d'expédition GlobalPost, où l'étiquette internationale et les documents douaniers sont apposés, puis des partenaires postaux et commerciaux assurent le trajet jusqu'à la poste ou au transporteur local qui te livre. Le vendeur te communique le numéro, qui commence généralement par `GP` : colle-le dans le champ ci-dessus. Le traceur officiel se trouve sur `goglobalpost.com/tracking` ; dans le catalogue de Peek, chercher `Go GlobalPost`, `GoGlobalPost`, `Global Post`, `Go Global Post` ou `GlobalPost UK` trouve aussi GlobalPost.

## Numéro de suivi GlobalPost : à quoi il ressemble

Peek reconnaît les numéros actuels de GlobalPost par leur structure : `GP`, quatre lettres désignant le service, dix chiffres, puis `US` ou `GB` selon le pays d'expédition. Avec des lettres et chiffres inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `GPECON4829105736US` | `GP`, quatre lettres, dix chiffres et `US` : un colis GlobalPost expédié depuis les États-Unis. |
| `GPSTND4829105736GB` | Même format terminé par `GB` : expédié depuis le Royaume-Uni. |
| `SDC482910573649182` | `SDC` et 15 chiffres : une ancienne étiquette GlobalPost. Peek ne désigne aucun transporteur à partir du seul format : sélectionne GlobalPost avec « Changer ». |
| `GPECON482910573US` | Seulement neuf chiffres : Peek ne reconnaît aucun transporteur. Vérifie s'il manque un chiffre. |
| `LZ482910576US` | Un numéro postal américain, pas un format GlobalPost : Peek désigne [USPS](carrier:usps). Si tu choisis GlobalPost manuellement pour ce numéro, Peek indique qu'il « n’émet pas de numéros comme celui-ci ». |

Peek ignore les espaces, les tirets et les minuscules.

**Où le trouver.** Dans l'e-mail de confirmation d'envoi du vendeur ou sur la page de ta commande. Si tu ne le trouves pas, vois [comment trouver son numéro de suivi](guide:find-tracking-number) et [les formats des numéros de suivi](guide:tracking-number-formats).

**Un second numéro.** Sous `Final Mile Tracking By:`, le traceur de GlobalPost peut afficher le numéro propre au transporteur final et permet aussi d'ouvrir le suivi par ce numéro (tandis que Peek n'interroge GlobalPost qu'avec un numéro `GP` ou `SDC`).

## Statuts de suivi GlobalPost expliqués

Le traceur de GlobalPost présente les scans du plus récent au plus ancien, regroupés par journée, avec l'heure, un intitulé en anglais et le plus souvent un lieu, en reformulant les scans des partenaires dans ses propres termes. Dans l'ordre habituel :

| GlobalPost indique | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Label Created` | Le vendeur a créé l'étiquette ; rien n'a encore bougé. | « Étiquette créée » |
| `Data Received` | GlobalPost a reçu les données électroniques du vendeur, pas encore le colis. | « Étiquette créée » |
| `Arrived at Partner Facility`, `Departed Partner Facility` | Un partenaire a scanné le colis : USPS en route vers GlobalPost, ou l'opérateur postal dans le pays d'arrivée. | « En transit » |
| `Arrived at GlobalPost Facility` | Un centre de tri GlobalPost a reçu le colis (des scans USPS d'approche peuvent précéder cette étape). | « Pris en charge » |
| `Departed GlobalPost Facility` | Le colis a quitté ce centre GlobalPost. | « En transit » |
| `Departed from Origin Country` | Il a quitté les États-Unis ou le Royaume-Uni. | « En transit » |
| `In Transit`, `In-Transit` | En cours d'acheminement. | « En transit » |
| `Arrived in Destination Country` | Arrivé dans le pays de destination. | « En transit » |
| `In Customs` | En cours de contrôle douanier à l'importation. | « À la douane » |
| `Cleared Customs` | Dédouanement terminé ; le colis repart. | « En transit » |
| `Out for Delivery` | En tournée de livraison aujourd'hui. | « En livraison » |
| `Attempted Delivery` | Tentative de remise infructueuse. | « Tentative de livraison » |
| `Exception, No Action Required` | Incident de livraison dans le pays d'arrivée ne nécessitant aucune démarche de ta part. | « Problème signalé » |
| `Delayed, No Action Required` | Retard annoncé sans action requise de ton côté. | « Problème signalé » |
| `Delivered` | Colis livré (ou retourné à l'expéditeur : voir ci-dessous). | « Livré » |

Une ligne peut se terminer par une mention de GlobalPost signalant que les mises à jour prennent parfois du temps : Peek lit le statut qui précède tout en conservant le texte complet, et une ligne d'enregistrement apparue après la mise en route ne fait pas reculer le statut. Les autres libellés suivent les règles générales de Peek.

**Retours.** Le traceur de GlobalPost termine aussi un retour par `Delivered`, en affichant la ville du destinataire dans l'en-tête alors que le scan lui-même porte la ville de l'expéditeur. Lorsque la ville du scan correspond à celle de l'expéditeur dans un autre pays et une autre ville que ceux du destinataire, Peek affiche « Retourné à l’expéditeur ».

## Qui livre les colis GlobalPost en France, en Belgique et en Suisse

GlobalPost remet ses envois *Economy* et *Standard* à un opérateur postal (tel qu'USPS au départ des États-Unis) pour le trajet international. Ses pages ne publient pas de liste de livreurs par pays, mais son traceur peut nommer l'opérateur final et donner son numéro.

**Ce que montre le suivi dépend du service** choisi par le vendeur :

| Service | Suivi inclus | Droits et taxes | Délai annoncé au vendeur |
| --- | --- | --- | --- |
| `Economy` | jusqu'à l'entrée au pays et la sortie de douane | à ta charge (`DDU`) | `10 – 14 business days` |
| `Standard` | jusqu'à ta porte | à ta charge (`DDU`) | `6 – 10 business days` |
| `Plus`, `Plus – Duties & Taxes Included` | jusqu'à ta porte | payés par l'expéditeur (`DDP`) | `5 – 8 business days` |
| `Flat Rate (formerly Go)` | jusqu'à ta porte | inclus dans le tarif expéditeur | `6 – 10 business days` |

Au départ des États-Unis, *GlobalPost Plus – Duties & Taxes Included* couvre notamment la **France** et la **Belgique** (ainsi que le Canada et d'autres pays européens), tandis que *Flat Rate (formerly Go)* dessert le Canada, l'Australie, la Nouvelle-Zélande, Hong Kong et le Mexique. Les tentatives de remise et le retrait suivent les règles de l'opérateur local : vois [La Poste](carrier:la-poste), [bpost](carrier:bpost), [La Poste suisse](carrier:swiss-post) ou [Postes Canada](carrier:canada-post), ainsi que [livraison manquée](guide:missed-delivery) et [colis non réclamé](guide:uncollected-parcel).

**Douane dans l'UE, en France, en Belgique et en Suisse.** Un colis expédié depuis les États-Unis ou la Grande-Bretagne passe en douane à l'entrée dans l'UE ou en Suisse (l'**Irlande du Nord**, en revanche, est traitée comme un territoire de l'UE pour la douane et la TVA sur les marchandises, selon la Commission européenne). Sur *Economy* et *Standard* (*Delivered Duties Unpaid*), les droits et taxes sont à la charge de l'acheteur. Dans l'**Union européenne** (**France** et **Belgique**), depuis le 1er juillet 2026, les biens achetés en ligne hors UE dans des envois d'une valeur intrinsèque maximale de 150 € supportent un droit de douane de 3 € par article ; depuis le 1er novembre 2026 s'ajoutent des frais de traitement de l'Union de 2 € par article, quelle que soit la valeur (un article regroupe les biens d'un même colis partageant classement tarifaire, désignation et, si requise, origine ; au-delà de 150 €, tarif douanier normal). Selon la douane française, en **France métropolitaine**, la TVA s'applique « dès le premier euro » (sauf si elle a déjà été facturée lors de l'achat via le guichet IOSS) ; en **Guadeloupe**, **Martinique** et **La Réunion**, elle s'applique si la valeur totale « dépasse 22 euros », et elle « n'est provisoirement pas applicable dans les départements de la Guyane et de Mayotte » (hors octroi de mer et droits au-delà de 150 €). En **Suisse**, selon l'OFDF, la TVA est de 8,1 % (2,6 % au taux réduit), mais « les droits de douane inférieurs à 5 francs ne sont pas perçus » et « les montants d'impôt inférieurs à 5 francs ne sont pas perçus », hors frais éventuels du transporteur. Les pages de GlobalPost ne précisent pas comment les frais de 2 € sont refacturés : demande au vendeur si ton prix les incluait, et consulte [colis bloqué en douane](guide:customs).

## Colis GlobalPost bloqué, perdu ou retourné

**« En attente de GlobalPost ».** Quand le traceur renvoie qu'il ne trouve aucun détail pour ce numéro (`status=not_found`), Peek affiche « En attente de GlobalPost » et continue de vérifier. Si GlobalPost ne connaît toujours pas le numéro après deux jours et qu'aucun service de suivi ne le trouve non plus, Peek affiche : « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » Avec seulement `Label Created`, demande au vendeur après quelques jours si le colis a bien été expédié.

**Plus de mise à jour pendant plusieurs jours.** GlobalPost prévient que les mises à jour internationales prennent parfois du temps (météo, jours fériés, grèves, douane) et qu'en *Economy*, le suivi s'arrête une fois la douane franchie dans le pays d'arrivée. Après quatre jours sans nouveau scan, Peek signale « Aucune mise à jour depuis quatre jours » et rappelle qu'un colis reste souvent silencieux sur un long vol, en douane ou le week-end. GlobalPost n'ayant pas de page d'aide dédiée dans le catalogue, ce message n'affiche de lien d'aide qu'une fois le colis suivi chez le transporteur final (si Peek en connaît un). Vois [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

**Toujours rien après 30 jours.** GlobalPost demande de contacter le vendeur si le colis n'est pas arrivé 30 jours après l'expédition : seul le vendeur peut déposer une réclamation auprès de GlobalPost dans les `90 days` suivant l'envoi, avec un traitement pouvant prendre jusqu'à `3 weeks`. Vois [colis perdu](guide:lost-parcel).

**Indiqué livré mais absent, ou non distribuable / retourné.** Pour un colis marqué livré mais introuvable, interroge d'abord le transporteur local, puis le vendeur ([colis indiqué livré mais non reçu](guide:delivered-not-received)). En cas d'adresse erronée ou incomplète, de droits ou taxes impayés, d'avis douanier manqué ou d'articles restreints, la poste de destination conserve ou renvoie le colis sans que GlobalPost reçoive de nouveau numéro ni de délai de retour ; les conditions générales de GlobalPost excluent les colis non distribuables (*undeliverable*) des indemnisations, donc vois le remboursement avec le vendeur ([colis retourné à l'expéditeur](guide:returned-to-sender)).

## Service client et contact GlobalPost

Le service client de GlobalPost répond sur sa page *Contact Support* :

- **E-mail** : `info@goglobalpost.com`.
- **Téléphone** : `(888) 899-1255`, du lundi au vendredi de `6am` à `6pm` heure du Pacifique (*Pacific Time*).
- **Formulaire de contact** : sur `www.goglobalpost.com/contact-support/`, choisis `Recipient` et saisis ton numéro de suivi (GlobalPost précise que son équipe voit les mêmes informations que la page publique de suivi).

Pour un colis en retard ou manquant, GlobalPost renvoie d'abord vers le vendeur qui a réservé l'envoi. GlobalPost précise aussi qu'un colis parti de Chine avec [China Post](carrier:china-post) n'est pas un envoi GlobalPost : adresse-toi alors à l'expéditeur.

**Attention aux SMS frauduleux.** Le domaine officiel de GlobalPost est `www.goglobalpost.com` (`goglobalpost.com`). Avant de cliquer sur un lien reçu par SMS ou e-mail, consulte [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

## Suivre un colis GlobalPost avec Peek

[Peek](/) identifie immédiatement un numéro `GP` (« Transporteur détecté ») et interroge le traceur de GlobalPost sans compte ni code postal (le site met en cache une page trouvée pendant quelques minutes). Un lien `goglobalpost.com/track-detail/?t=` ou `/track/?t=` fonctionne aussi ; pour un ancien numéro `SDC`, choisis GlobalPost via « Changer ». Si tu n'as que le numéro du transporteur final, colle-le quand même : Peek suit plus de 3 500 transporteurs.

Peek affiche le statut, chaque scan avec son lieu (en coupant les codes postaux), le nom du service, le pays de destination ainsi que le transporteur final et son numéro lorsque GlobalPost les indique. Il écarte la barre de progression, les villes d'origine et de destination de l'en-tête (lues uniquement pour détecter un retour) et les codes postaux ; GlobalPost ne fournit ni date de livraison prévue, ni poids, ni preuve de livraison. Le bouton « Ouvrir le site GlobalPost » ouvre ton numéro sur `goglobalpost.com`.

Les horaires de GlobalPost n'indiquent pas de fuseau : sur un envoi parti des États-Unis, Peek lit les scans des centres GlobalPost opérés par ePost Global (`Town ST`) et les scans USPS de sortie dans les États américains à fuseau unique sur l'horloge de cet État et les convertit dans le fuseau de ton appareil, tandis que l'enregistrement, les centres gérés par Asendia (`Town, ST`), les envois partis du Royaume-Uni, les étapes à l'étranger et les retours gardent l'heure affichée par GlobalPost (minuit pile s'affiche comme une date sans heure) ; sur `peektracker.com` comme dans les applications iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : … ».

Quand GlobalPost nomme un transporteur final connu de Peek, la fiche indique « Livraison par … » et affiche son numéro après le code `GP`, ou « Annoncé à …, pas encore reçu » tant que ce transporteur répertorie le colis sans scan propre. Avec un second numéro sans transporteur reconnu par son nom, Peek interroge la poste du pays de destination pour un numéro postal valide ([La Poste](carrier:la-poste) en France, [bpost](carrier:bpost) en Belgique, [La Poste suisse](carrier:swiss-post) en Suisse) ou l'unique transporteur désigné par le format du numéro. Dès que ce transporteur montre un scan au moins aussi récent que le dernier de GlobalPost, Peek bascule dessus tout en conservant l'historique de GlobalPost.

Tant que tu gardes le colis ouvert ou, le jour de sa livraison, avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes (toutes les 5 minutes lorsqu'il doit ouvrir le traceur de GlobalPost dans un navigateur) dès 6 h locales le jour où le colis passe en « En livraison » tant que le traceur de GlobalPost répond (après un relais vers le transporteur final, Peek suit le rythme de celui-ci ; via les services de suivi seuls : toutes les heures, toutes les 3 heures après un jour calme et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont actives) ; sinon, et après deux jours sans nouveauté (12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Alertes disponibles : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni GlobalPost ni le vendeur pour toi.

## Questions fréquentes sur le suivi GlobalPost

### Pourquoi mon suivi GlobalPost s'arrête-t-il après la douane ?

Avec *GlobalPost Economy*, le suivi s'arrête une fois le colis arrivé dans le pays de destination et dédouané, et GlobalPost précise que certains envois ont un suivi limité sur place. Le colis poursuit néanmoins sa route ; si le traceur indique un numéro sous `Final Mile Tracking By:`, Peek interroge ce transporteur et tu peux le suivre aussi.

### Qui livre les colis GlobalPost ?

Une poste ou un transporteur local dans le pays d'arrivée, que le traceur de GlobalPost peut nommer avec son propre numéro de suivi. Lorsqu'un numéro postal international est fourni sans nom de partenaire reconnu, Peek interroge la poste du pays de destination (La Poste en France, bpost en Belgique, La Poste suisse en Suisse).

### Quel est le délai de livraison avec GlobalPost ?

Sa FAQ mentionne `5-6 days` vers les pays proches et jusqu'à `20 days` vers les destinations plus éloignées, et ses pages services annoncent aux vendeurs `10 – 14 business days` en *Economy*, `6 – 10 business days` en *Standard* et *Flat Rate*, et `5 – 8 business days` en *Plus*, à titre d'estimation après traitement au centre d'expédition.

### GlobalPost est-il fiable ?

Oui. GlobalPost appartient au groupe Auctane, est commercialisé via ShipStation et Stamps.com, expédie vers plus de 220 pays et revendique plus de 60 millions d'envois livrés.

### Comment contacter GlobalPost ?

Écris à `info@goglobalpost.com`, appelle le `(888) 899-1255` en semaine de `6am` à `6pm` heure du Pacifique ou utilise le formulaire `goglobalpost.com/contact-support/` en choisissant `Recipient`. Au-delà de 30 jours après l'envoi, GlobalPost demande de s'adresser d'abord au vendeur.

:::sources
- [GlobalPost : FAQs](https://www.goglobalpost.com/faqs/) – groupe Auctane, fonctionnement, services et pays couverts en Plus / Flat Rate, suivi Economy après douane, délais, retours et réclamations sous 90 jours / 3 semaines
- [GlobalPost : Standard](https://www.goglobalpost.com/standard/) – comparatif Economy, Standard, Plus et Flat Rate : suivi, droits DDU/DDP et jours ouvrés
- [GlobalPost : Contact Support](https://www.goglobalpost.com/contact-support/) – e-mail, téléphone, horaires Pacifique, option Recipient du formulaire et précision sur China Post
- [GlobalPost : Home](https://www.goglobalpost.com/) – plus de 220 pays et plus de 60 millions d'envois livrés
- [GlobalPost : Track your package](https://www.goglobalpost.com/tracking/) – traceur officiel et message d'absence de résultat
- [GlobalPost : Terms and Conditions](https://www.goglobalpost.com/conditions/) – centres d'expédition, délais estimatifs et exclusion des colis non distribuables
- [Commission européenne : Réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € depuis le 1er juillet 2026 et frais de traitement de 2 € depuis le 1er novembre 2026
- [Commission européenne : Orientations sur le droit de 3 € et les frais de l'Union (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – frais de 2 € quelle que soit la valeur, définition d'un article et exonération de TVA via IOSS
- [Commission européenne : Champ d'application territorial de la TVA et de la douane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Irlande du Nord
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA en France métropolitaine et dans les DOM
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse de 8,1 % / 2,6 % et non-perception des montants inférieurs à 5 francs
- [Universal Parcel Scraper : GlobalPost](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/globalpost) – formats GP et SDC, statuts, détection des retours, horloges, cache et partenaire final
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format USPS et relais vers la poste ou le transporteur de destination
:::
