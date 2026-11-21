---
title: Suivi ECMS Express : numéros à 16 caractères et statuts
description: Suis un colis ECMS Express (易客满) : format des numéros à 16 caractères, sens des scans anglais et chinois, livraison finale, douane et suivi avec Peek.
slug: suivi-ecms-express
published: 2026-11-21
updated: 2026-11-21
---

ECMS Express (`易客满`, aussi appelé `ECMS Global` ou `ECMSGlobal`, raison sociale chinoise `天津易客满国际物流有限公司`) est un transporteur express international fondé en 2013 qui achemine les commandes de boutiques en ligne vers plus de 200 pays et régions. Si un marchand a expédié ton achat avec ECMS, ton numéro compte 16 caractères et commence par `EC`, `ES`, `AP` ou `AX` : colle-le dans le champ ci-dessus. ECMS assure le transport aérien international et le dédouanement, puis confie le dernier kilomètre à un transporteur local. Dans le catalogue de Peek, chercher `ECMS`, `ECMS Global`, `ECMSGlobal`, `ECMS International Logistics Co.`, `易客满`, `易客满国际物流有限公司` ou `易客满国际快递` permet aussi de le trouver.

## Numéro de suivi ECMS Express : à quoi il ressemble

Les numéros ECMS comptent toujours 16 caractères : `EC`, `ES`, `AP` ou `AX`, suivis de trois à cinq lettres, puis de chiffres pour le reste. Avec des caractères inventés :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `ECLAX48291057364` | `EC`, trois lettres et onze chiffres : un numéro ECMS Express. |
| `ECSZXA4829105736` | `EC`, quatre lettres et dix chiffres : numéro ECMS également. |
| `ECSZXAB482910573` | `EC`, cinq lettres et neuf chiffres : numéro ECMS également. |
| `ESLAX48291057364`, `APLAX48291057364`, `AXLAX48291057364` | Les mêmes structures de 16 caractères commençant par `ES`, `AP` ou `AX`. |
| `ECLAX4829105736` | Seulement 15 caractères : ce n'est pas un format ECMS, et Peek ne reconnaît aucun transporteur (si tu choisis ECMS Express manuellement, Peek indique qu'il « n’émet pas de numéros comme celui-ci »). Vérifie s'il manque un chiffre. |

Aucun autre transporteur du catalogue de Peek n'utilise ces structures. Les espaces et les minuscules n'ont pas d'importance dans Peek.

**Où le trouver.** ECMS imprime le numéro de lettre de transport (*waybill*) sur l'étiquette du colis, et la boutique le transmet dans son e-mail d'expédition ou sur la page de commande. Si tu ne le trouves pas, consulte [comment trouver son numéro de suivi](guide:find-tracking-number) et [les formats des numéros de suivi](guide:tracking-number-formats).

**Un lien de suivi.** Un lien vers le traceur `ecmsglobal.com` se terminant par `?number=` préremplit ce numéro et ouvre la vérification par image (*Security verification*). D'autres liens ECMS portent le numéro après `orderNumber=`, `trackingno=` (dont `consignee.ecmsglobal.com`) ou `order_number=`. Colle n'importe lequel dans Peek : il en extrait automatiquement le numéro ECMS.

## Statuts de suivi ECMS Express expliqués

Sur `ecmsglobal.com`, le traceur demande d'abord de valider un contrôle visuel (*Security verification* : *Enter the characters shown below to continue tracking.*), résume ensuite l'envoi par l'un de ses huit libellés (de *Shipment Information Received* à *Delivered* ou *Exception*), puis liste les événements du plus récent au plus ancien avec date, heure et lieu. Peek affiche chaque scan avec le libellé enregistré dans le dossier ECMS. Dans l'ordre du parcours :

| Dossier ECMS | Ce que cela veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Virtual Manifest has been received by carrier`, `Virtual Manifest has been received`, `Shipment info confirmed` | La boutique a transmis les informations électroniques à ECMS, qui n'a pas encore reçu le colis. | « Étiquette créée » |
| `Documents and information required for clearance have been received by the carrier.`, `ID information has been reviewed and verified` | ECMS a reçu les documents nécessaires au dédouanement ou vérifié la pièce d'identité du destinataire ; le colis n'est toujours pas scanné. | « Étiquette créée » |
| `Shipment arrived at carrier facility.`, `Shipment arrived at origin facility.` | Premier scan physique du colis dans l'entrepôt d'origine d'ECMS. | « Pris en charge » |
| `Parcel has arrived at export distribution warehouse` | Arrivée à l'entrepôt d'exportation d'ECMS au départ. | « En transit » |
| `Shipment departed from carrier facility.`, `Shipment departed from origin facility.` | Le colis a quitté cet entrepôt d'origine. | « En transit » |
| `Flight departure`, `Flight arrival` | Décollage puis atterrissage du vol cargo. | « En transit » |
| `Arriving notification received from terminal` | Le terminal fret de l'aéroport d'arrivée confirme la réception de la cargaison du vol. | « En transit » |
| `Shipment has arrived at ECMS transit warehouse`, `Shipment has departed from ECMS transit warehouse` | Entrepôt de transit d'ECMS à l'aéroport d'arrivée, avant puis après le dédouanement d'importation. | « En transit » |
| `Shipment started customs clearance process.` | La douane du pays de destination examine l'envoi. | « À la douane » |
| `Shipment completed customs clearance process.` | Le dédouanement d'importation est terminé ; le colis repart. | « En transit » |
| `Shipment arrived at destination facility`, `Parcel at destination facility, ready for next step`, `Parcel Weight` | Arrivée au centre de destination, pesée et préparation pour le transporteur local. | « En transit » |
| `Shipment is tendering to third party carrier for final delivery.` | ECMS remet le colis au transporteur local chargé de la livraison finale, dont le numéro peut apparaître dès cette étape. | « En transit » |
| `Return to origin` | Incident signalé au centre de destination, qui ne signifie pas toujours un renvoi effectif : dans un historique connu du code de Peek, le transporteur local a pris le colis le lendemain et la livraison s'est poursuivie. | « Problème signalé » |
| `Local carrier has accepted package for final delivery` | Le transporteur local a pris le colis en charge. | « En transit » |
| `Shipment is in Transit within the carrier‘s network`, `Parcel in transit during last mile delivery` | Acheminement dans le réseau du transporteur local tel que relayé par ECMS (la seconde ligne peut réapparaître sur plusieurs jours). | « En transit » |
| `Shipment is out for delivery.` | Colis en tournée de livraison. | « En livraison » |
| `Available for pickup` | Le transporteur local garde le colis à ta disposition en point de retrait. | « Prêt à être retiré » |
| `Shipment delivered to customer` | Colis livré. | « Livré » |

**Lignes en chinois.** Certains dossiers enregistrent ces étapes en chinois ; Peek affiche le texte chinois tel quel et lui attribue la même étape qu'à son équivalent anglais : `发货预报已收到，正在等待货物到达我们的始发站仓库` (« Étiquette créée »), `干线航班起飞` et `干线航班已到达` (« En transit »), `货物已进入清关流程` (« À la douane »), `货物已清关放行`, `货物已到达目的站仓库` et `货物出库前往配送中心安排最终派送` (« En transit »).

**Horaires.** ECMS horodate ses propres scans avec l'heure locale et le décalage UTC de la station qui les enregistre, et Peek conserve ce décalage. Les scans relayés du transporteur local portent un décalage zéro : Peek les lit en UTC, sauf si un scan de ce flux tomberait ainsi plus de dix minutes après son enregistrement chez ECMS, auquel cas Peek traite ces horaires comme une heure locale datée d'après le lieu. Une ligne de vol (`Flight departure`, `Flight arrival`) horodatée à minuit pile ne donne que le jour du vol et s'affiche dans Peek sans heure. Vois aussi [suivre un colis venu de Chine](guide:tracking-from-china).

## Qui livre les colis ECMS Express en France, en Belgique et en Suisse

Pour le dernier kilomètre (*Final Mile Delivery*), ECMS indique sur sa page *Parcel Delivery Service* s'associer à des transporteurs locaux de confiance (*trusted local carriers*), sans publier de liste nominative par pays en Europe. Sur le traceur d'ECMS, l'encadré *Last Mile Delivery* peut afficher le nom de ce transporteur sous `LM Provider`, son numéro sous `LM Tracking No.` et un bouton `Track with …` (dans les dossiers connus du code de Peek aux États-Unis, il s'agit par exemple de SpeedX).

**Délais annoncés.** Selon la FAQ d'ECMS, le délai dépend de l'origine, de la destination et du service choisi. Au départ des États-Unis (*U.S. Origin* sur la page *Parcel Delivery Service*), ECMS indique à titre indicatif `4–8 business days` vers l'**Union européenne** et vers la **Suisse** (ainsi que `3–6 business days` vers le Royaume-Uni et `3–11 business days` vers le Canada). Les modalités de passage et de garde en point de retrait suivent les règles du transporteur local : consulte [livraison manquée](guide:missed-delivery) et [colis non réclamé](guide:uncollected-parcel).

**Douane dans l'UE, en France, en Belgique et en Suisse.** La FAQ d'ECMS rappelle que sur un envoi `DDU/DAP`, le destinataire peut devoir régler les droits et taxes avant la livraison, tandis qu'en `DDP` l'expéditeur les paie généralement d'avance (certains pays exigent aussi une pièce d'identité du destinataire pour dédouaner). Dans l'**Union européenne** (**France** et **Belgique**), depuis le 1er juillet 2026, les biens achetés en ligne hors UE dans des envois d'une valeur intrinsèque maximale de 150 € supportent un droit de douane de 3 € par article ; depuis le 1er novembre 2026 s'ajoutent des frais de traitement de l'Union de 2 € par article, quelle que soit la valeur (un article regroupe les biens d'un même colis partageant classement tarifaire, désignation et, si requise, origine ; au-delà de 150 €, tarif douanier normal). Selon la douane française, en **France métropolitaine**, la TVA s'applique « dès le premier euro » (sauf si elle a déjà été facturée lors de l'achat via le guichet IOSS) ; en **Guadeloupe**, **Martinique** et **La Réunion**, elle s'applique si la valeur totale « dépasse 22 euros », et elle « n'est provisoirement pas applicable dans les départements de la Guyane et de Mayotte » (hors octroi de mer et droits au-delà de 150 €). En **Suisse**, selon l'OFDF, la TVA est de 8,1 % (2,6 % au taux réduit), mais « les droits de douane inférieurs à 5 francs ne sont pas perçus » et « les montants d'impôt inférieurs à 5 francs ne sont pas perçus », hors frais éventuels du transporteur. Consulte [colis bloqué en douane](guide:customs).

## Colis ECMS Express bloqué, en retard ou non reçu

**« We could not find a shipment for this tracking number. »** Le traceur d'ECMS ne connaît pas encore ce numéro (erreur de saisie ou commande pas encore transmise par le marchand) : Peek affiche « En attente de ECMS Express » et continue de vérifier. Si ECMS ne reconnaît toujours pas le numéro au bout de deux jours et qu'aucun service de suivi ne le trouve non plus, Peek affiche : « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. »

**Bloqué sur `Virtual Manifest has been received`.** ECMS a reçu l'annonce électronique, pas le colis physique. Demande à la boutique quand elle a remis ta commande.

**Plus rien après `Shipment is tendering to third party carrier for final delivery.`** Le transporteur local prend le relais et son propre suivi peut être plus avancé. Repère son nom et son `LM Tracking No.` dans l'encadré *Last Mile Delivery* et, si Peek n'y suit pas déjà le colis, ajoute ce numéro séparément dans Peek. Au bout de quatre jours sans nouveau scan, Peek affiche le jour du dernier scan (« Aucune mise à jour depuis lun 12 oct ») et le lien « Demande de l’aide à ECMS Express » (ou celui du transporteur local si Peek le suit déjà et connaît sa page d'aide). Plus de détails dans [pourquoi le suivi ne bouge plus](guide:tracking-not-updating).

**Indiqué livré mais rien reçu, perdu, endommagé ou retourné.** Interroge d'abord le transporteur local sur le lieu de dépôt, puis contacte le vendeur : la FAQ d'ECMS renvoie les acheteurs vers le marchand pour tout remplacement ou remboursement, car seul l'expéditeur peut déposer une réclamation écrite auprès d'ECMS dans les 30 jours suivant l'acceptation de l'envoi (une seule réclamation par envoi), et ECMS ne peut ni annuler ni modifier une commande. Parmi les causes de retour citées par ECMS figurent les articles interdits ou restreints, une adresse incomplète ou erronée, un blocage douanier, des droits et taxes impayés ou des formalités douanières non accomplies par le destinataire — mais une seule ligne `Return to origin` ne suffit pas à confirmer un renvoi définitif. Vois [colis indiqué livré mais non reçu](guide:delivered-not-received), [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender).

## Service client et contact ECMS Express

Selon la page *Contact Us* de `www.ecmsglobal.com` (sans fuseau horaire précisé pour les horaires ; la France, la Belgique et la Suisse n'ont pas de numéro national propre) :

- **Formulaire en ligne** : sur `www.ecmsglobal.com/en-us/contact-us.html`, choisis `Shipment Support` dans *Inquiry Type* et renseigne ton numéro dans *Tracking Number (Optional)*.
- **Europe (Royaume-Uni)** : `(+44) 01753528888`, `cs.uk@ecmsglobal.com`, du lundi au vendredi de `08:30` à `17:00`.
- **Chine continentale** : `(+86) 400 086 1756`, `service@ecmsglobal.com`, du lundi au vendredi de `08:30` à `17:30`.
- **États-Unis** : `(+1) 626 956 5388`, `customerserviceus@ecmsglobal.com`, du lundi au vendredi de `09:00` à `18:00`.

**Attention aux SMS frauduleux.** Les domaines officiels d'ECMS utilisés pour le suivi sont `www.ecmsglobal.com` (`ecmsglobal.com`) et l'ancien portail `consignee.ecmsglobal.com`. Peek écarte tout lien de paiement figurant sur un dossier ; si tu reçois un message réclamant des frais de douane ou de livraison, consulte [reconnaître les SMS de livraison frauduleux](guide:delivery-scam-texts).

## Suivre un colis ECMS Express avec Peek

[Peek](/) reconnaît immédiatement un numéro ECMS de 16 caractères (« Transporteur détecté ») et interroge le traceur d'ECMS sans compte, code postal ni téléphone.

Le contrôle visuel d'ECMS est une petite addition ou soustraction à un chiffre : Peek la résout lui-même et, en cas d'échec, réessaie une fois avec une nouvelle image. Si le traceur d'ECMS reste inaccessible, Peek interroge des services de suivi en relais et affiche l'historique qu'ils détiennent, dans leurs propres termes.

Peek affiche le statut, chaque scan avec sa ville, sa région et son pays ainsi que le pays de destination, du plus récent au plus ancien, dans le fuseau horaire de ton appareil ; sur `peektracker.com` comme dans les applications iPhone et Android, l'historique se termine par « Heures dans ton fuseau horaire : … ». Peek écarte la référence du marchand, le lieu sur les lignes de manifeste électronique et tout lien de paiement, et n'affiche ni poids ni date de livraison estimée. Le bouton « Ouvrir le site ECMS Express » ouvre ton numéro sur `www.ecmsglobal.com`.

Lorsque le dossier ECMS nomme un transporteur local connu de Peek, la fiche indique « Livraison par … » (avec son numéro lorsqu'un numéro unique figure dans les scans partenaires relayés), ou « Annoncé à …, pas encore reçu » tant que ce transporteur répertorie le colis sans scan propre. Si Peek lit le traceur direct de ce transporteur sans donnée supplémentaire, il l'interroge aussi et bascule dessus dès qu'un scan au moins aussi récent que le dernier d'ECMS apparaît, tout en gardant l'historique d'ECMS ; sinon, ajoute le `LM Tracking No.` à part : Peek suit plus de 3 500 transporteurs.

Tant que tu gardes le colis ouvert ou, le jour de sa livraison, avec les notifications activées, Peek vérifie les mises à jour jusqu'à toutes les 10 minutes, et jusqu'à toutes les 2 minutes dès 6 h locales le jour où il passe en « En livraison » tant que le traceur d'ECMS répond (après un relais vers le transporteur local, Peek suit le rythme de celui-ci ; via les services de suivi seuls : toutes les heures, toutes les 3 heures après un jour calme et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont actives) ; sinon, et après deux jours sans nouveauté (12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Notifications au choix : « Chaque scan », « Étapes importantes » (l'entrée en douane d'importation, `Return to origin`, la tournée, `Available for pickup` et la livraison, sans les vols ni les passages d'entrepôt) ou « Livraison seulement ». Peek ne peut contacter ni ECMS ni le vendeur pour toi.

## Questions fréquentes sur le suivi ECMS Express

### Qu'est-ce qu'ECMS Express (易客满) ?

Une entreprise internationale de transport express et de logistique transfrontalière fondée en 2013 (`天津易客满国际物流有限公司`), disposant de ses propres succursales en Chine continentale, à Hong Kong, à Singapour, aux États-Unis, au Royaume-Uni, en Europe, en Corée du Sud et au Japon.

### Où suivre un colis ECMS Express ?

Dans le champ en haut de cette page (sans avoir à saisir de code visuel) ou sur la page *Track Your Shipment* de `www.ecmsglobal.com` après avoir validé la vérification par image.

### Qui livre les colis ECMS Express en France, en Belgique et en Suisse ?

Un transporteur partenaire local avec lequel travaille ECMS, dont le nom et le numéro local peuvent s'afficher sous `LM Provider` et `LM Tracking No.` dans l'encadré *Last Mile Delivery* du suivi ECMS. S'ils n'apparaissent pas encore, demande-les au vendeur.

### Que signifie « Shipment is tendering to third party carrier for final delivery » ?

ECMS est en train de remettre ton colis au transporteur local chargé de la livraison finale ; son numéro de suivi propre peut apparaître sous `LM Tracking No.`. Peek affiche « En transit ».

### Qui contacter pour un colis ECMS Express manquant ?

Contacte d'abord le marchand chez qui tu as commandé : la FAQ d'ECMS renvoie les acheteurs vers le vendeur pour tout remboursement ou renvoi, et seul l'expéditeur peut réclamer auprès d'ECMS sous 30 jours. Pour la tournée finale, interroge le transporteur local ; tu peux aussi écrire à ECMS via le formulaire *Shipment Support* sur `ecmsglobal.com`.

:::sources
- [ECMS Express : page d'accueil](https://www.ecmsglobal.com/en-us/home.html) – express international, fret et logistique pour l'e-commerce
- [ECMS Express : About ECMS](https://www.ecmsglobal.com/en-us/about.html) – fondation en 2013
- [易客满 : 关于我们 (à propos)](https://www.ecmsglobal.com/zh-cn/about.html) – fondation en 2013, succursales propres et raison sociale 天津易客满国际物流有限公司
- [ECMS Express : Parcel Delivery Service](https://www.ecmsglobal.com/en-us/express.html) – partenaires locaux du dernier kilomètre, 200+ pays et délais au départ des États-Unis vers l'UE, la Suisse, le Royaume-Uni et le Canada
- [ECMS Express : Track Your Shipment](https://www.ecmsglobal.com/en-us/tracking.html) – vérification par image, 8 libellés de résumé, encadré Last Mile Delivery (`LM Provider`, `LM Tracking No.`), message d'absence et paramètre `?number=`
- [ECMS Express : FAQ](https://www.ecmsglobal.com/en-us/faq.html) – numéro sur l'étiquette, DDU/DAP et DDP, motifs de retour, impossibilité d'annuler, recours vers le vendeur et réclamation par l'expéditeur sous 30 jours
- [ECMS Express : Contact Us](https://www.ecmsglobal.com/en-us/contact-us.html) – formulaire Shipment Support, numéros de téléphone, e-mails et horaires par région
- [Commission européenne : Réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € depuis le 1er juillet 2026 et frais de traitement de 2 € depuis le 1er novembre 2026
- [Commission européenne : Orientations sur le droit de 3 € et les frais de l'Union (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – calcul par article et exonération de TVA via IOSS
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA en France métropolitaine et dans les DOM
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse de 8,1 % / 2,6 % et montants inférieurs à 5 francs non perçus
- [Universal Parcel Scraper : ECMS Express](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ecms-express) – alias, format à 16 caractères, liens, calcul visuel et nouvel essai, statuts anglais et chinois, horloges et champs écartés
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – relais vers le transporteur local et services de suivi en secours
:::
