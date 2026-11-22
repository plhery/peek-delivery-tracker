---
title: Suivi Stone3PL : numéros, statuts et contact
description: Suivi d’un colis Stone3PL depuis la Chine : format du numéro S, sens des statuts, transporteur local dans ton pays, droits de douane et contacts Stone3PL.
slug: suivi-stone3pl
published: 2026-11-22
updated: 2026-11-22
---

Stone3PL est une société de logistique basée à Shenzhen, en Chine, appartenant au groupe Stone. Elle expédie des commandes pour des vendeurs en ligne et d’autres entreprises depuis la Chine vers le monde entier, en partenariat avec d’autres prestataires logistiques. Si une boutique a expédié ta commande avec Stone3PL, consulte ton numéro sur [la page de suivi de Stone3PL](https://stone3pl.com/?route=services/track) (en anglais) ou colle-le dans la case ci-dessus. Le numéro est fourni par le vendeur dans les informations d’expédition de ta commande.

## Numéro de suivi Stone3PL : à quoi il ressemble

Stone3PL ne publie pas de format officiel. Peek associe une forme à Stone3PL : la lettre `S` suivie de 18 chiffres. Les deux exemples ci-dessous sont inventés :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `S482917365428193642` | `S` et 18 chiffres. Peek interroge Stone3PL pour les numéros de cette forme. Ce format seul ne prouve toutefois pas que le colis est chez Stone3PL. |
| `S48291736542819364` | Seulement 17 chiffres : ce n’est pas le format associé par Peek à Stone3PL. Vérifie s’il manque un chiffre, ou colle-le tout de même. |

Dans Peek, les minuscules, les espaces et les tirets n’ont pas d’importance. L’outil de Stone3PL accepte jusqu’à 40 numéros à la fois, un par ligne ; il ne conserve que les lettres, chiffres et tirets, et convertit les minuscules en majuscules.

**Où le trouver.** Dans l’e-mail d’expédition du vendeur ou sur ta page de commande. S’il n’y figure pas, lis [où trouver ton numéro de suivi](guide:find-tracking-number).

**Autres numéros.** La page de suivi de Stone3PL liste les transporteurs que son outil suit, notamment DHL Express, UPS, USPS, China Post et China EMS (ePacket). Le résultat peut aussi nommer la société qui livre dans ton pays avec son propre numéro, que tu peux suivre directement sur son site.

## Signification des statuts de suivi Stone3PL

Les lignes de suivi de Stone3PL sont en anglais, avec une date et une heure mais sans lieu. La page de résultats affiche d’abord les lignes du pays de destination, puis celles du début du trajet. Voici les lignes que Peek connaît :

| Stone3PL indique | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Order Submited.` | Le vendeur a transmis la commande à Stone3PL. Stone3PL n’a peut-être pas encore le colis. L’orthographe vient de Stone3PL. | « Étiquette créée » |
| `Shipment information received` | Stone3PL a reçu les données du colis. | « Étiquette créée » |
| `Processing information input` | Stone3PL enregistre les données du colis. | « Étiquette créée », ou « En transit » après `Pickup Scan` |
| `Pickup Scan` | Le colis a été collecté et scanné. Il est désormais en route. | « Pris en charge » |
| `facility - Outbound` | Le colis a quitté un entrepôt ou un centre de tri. | « En transit » |
| `Arrived at domestic terminal station` | Arrivé à un terminal. La ligne ne précise pas le pays. | « En transit » |
| `Port of departure` | Au port ou à l’aéroport de départ, avant d’embarquer. | « En transit » |
| `International shipment release - Export` | Dédouané à l’export, le colis poursuit sa route. | « En transit » |
| `Port of departure - Departure` | A quitté le port ou l’aéroport de départ. | « En transit » |
| `Port of destination - Arrival` | Arrivé dans un port ou aéroport du pays de destination. | « En transit » |
| `International shipment release - Import` | Dédouané à l’import, le colis continue son trajet. | « En transit » |
| `Pick up by local carrier at destination port` | Le transporteur local chargé de livrer dans ton pays a récupéré le colis au port. Tu n’as rien à aller chercher. | « En transit » |
| `Batch delivery to carrier` | Stone3PL a remis le colis, en lot avec d’autres, au transporteur final. | « En transit » |

Les lignes issues du pays de destination peuvent employer d’autres termes. Peek reconnaît les mots courants comme la livraison ou la douane. Une ligne que Peek ne reconnaît pas reste dans l’historique, et le statut reste sur la dernière étape comprise.

**Statut global.** Le suivi de Stone3PL attribue aussi un statut général à chaque colis. Lorsque ce statut indique un colis prêt au retrait, livré ou en incident, Peek affiche « Prêt à être retiré », « Livré » ou « Problème signalé », même avant qu’une ligne précise ne le dise.

**Heures.** Stone3PL fournit les heures sans fuseau horaire. Peek date les lignes du début du trajet à l’heure de Chine (UTC+8), et celles du pays de destination ainsi que `International shipment release - Import` à l’heure de ce pays (sur un seul de ses fuseaux s’il en a plusieurs), puis les affiche dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom).

## Qui livre les colis Stone3PL

Stone3PL indique travailler en partenariat avec des prestataires logistiques mondiaux reconnus, et dispose d’entrepôts dans les régions NORAM, APAC et EMEA (Amérique du Nord, Asie-Pacifique, et Europe, Moyen-Orient et Afrique).

Ses pages ne précisent pas quel transporteur livre dans quel pays : regarde sur la page de résultats de Stone3PL le nom du transporteur final et son numéro. Les tentatives de livraison, avis de passage et points relais suivent les règles de ce transporteur. Voir [livraisons manquées](guide:missed-delivery).

**Droits et douane.** Stone3PL propose aux vendeurs l’expédition DDP (Delivered Duty Paid), avec dédouanement DDP. Seul le vendeur peut te confirmer si ta commande a été envoyée selon ce régime.

**Dans l’UE (en Irlande, par exemple).** Les achats en ligne en provenance hors UE supportent un droit de douane de 3 € par article sur les envois d’une valeur allant jusqu’à 150 €, depuis le 1er juillet 2026. Des frais de traitement de l’Union de 2 € par article, quelle que soit la valeur, s’appliquent également depuis le 1er novembre 2026. Un article désigne les marchandises partageant une même classification tarifaire, description et, si requis, origine. Les pages de Stone3PL n’indiquent pas comment ces montants sont acquittés. Au Royaume-Uni, aux États-Unis, au Canada ou en Australie, ce qui est dû dépend des règles nationales : lis [colis bloqué à la douane](guide:customs).

## Colis Stone3PL bloqué, perdu ou retourné

Stone3PL travaille pour les entreprises qui expédient avec lui et ne publie aucune page d’aide pour les destinataires : pour un colis en retard, perdu, abîmé ou retourné, commence par contacter le vendeur.

**Rien pour l’instant.** Peek continue de vérifier. Il affiche « En attente de Stone3PL » si tu as choisi Stone3PL, et « Aucun historique de suivi pour le moment » si aucun transporteur n’était désigné à l’ajout. Après deux jours sans rien, Peek signale un colis ajouté sans transporteur (« En attente de la première mise à jour du transporteur ») : vérifie le numéro auprès du vendeur. Si tu as choisi Stone3PL et qu’il ne connaît toujours pas le numéro deux jours plus tard sans autre source disponible, Peek affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et vérifie ensuite chaque jour, puis chaque semaine passé sept jours.

**Uniquement `Order Submited.` ou `Shipment information received`.** Stone3PL a les données de la commande, mais peut-être pas encore le colis. Si rien ne bouge au bout de plusieurs jours, demande au vendeur si le colis a bien été remis.

**Aucune mise à jour depuis des jours.** Lorsqu’un colis en « Étiquette créée », « Pris en charge » ou « En transit » n’a aucun nouveau scan depuis quatre jours, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») ainsi que « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Stone3PL. », avec le lien « Demande de l’aide à Stone3PL » vers sa page de contact. Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating) et [suivre des colis venant de Chine](guide:tracking-from-china).

**« Problème signalé ».** Stone3PL a signalé une anomalie. Lis la dernière ligne, puis demande au vendeur quelle est la suite.

**Livré, mais introuvable.** Vérifie autour de chez toi et demande aux voisins, puis préviens le vendeur. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Perdu, abîmé ou retourné.** Le site de Stone3PL ne donne aucune procédure de réclamation ou de retour pour les destinataires ; sa page d’accueil propose aux vendeurs un service de retour et réexpédition. Demande au vendeur un remboursement ou un nouvel envoi. Voir [colis perdu](guide:lost-parcel), [colis abîmé](guide:damaged-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

**Message te demandant de payer.** Avant de régler des frais réclamés par SMS ou e-mail, lis [arnaques aux faux SMS de livraison](guide:delivery-scam-texts).

## Contacter Stone3PL

Sur [la page de contact de Stone3PL](https://stone3pl.com/index.php?route=services/contact_us), pour son siège de Shenzhen (« STONE3PL Marketing Dpt ») :

- **E-mail :** support@stone3pl.com. La politique de confidentialité de Stone3PL indique la même adresse.
- **Téléphone :** 86-0755-81466633, un numéro à Shenzhen, en Chine. Stone3PL n’indique ni horaires d’ouverture ni langues parlées.
- **Adresse :** Ground Floor, Building C5, Xin'an Industry Co., No.2 Industrial Zone, Gushu 2nd Rd., Baoan, Shenzhen, Guangdong Province, China.

Pour ta commande, adresse-toi d’abord au vendeur : c’est lui qui a contracté Stone3PL, pas toi.

## Suivre un colis Stone3PL avec Peek

[Peek](/) suit les colis Stone3PL, mais ne peut pas reconnaître un numéro Stone3PL d’après sa seule forme. Lorsque tu colles un numéro composé de `S` et de 18 chiffres, Peek affiche « Vérification des services de suivi… » pendant qu’il interroge Stone3PL. Si Stone3PL a le colis, la ligne du transporteur affiche « Stone3PL a ce colis. » et Peek le suit directement là. Un lien vers le suivi de Stone3PL avec un numéro après `nums=` identifie Stone3PL immédiatement (« Transporteur détecté »).

Pour tout autre numéro, colle-le quand même : Peek suit plus de 4 500 transporteurs. Quand une forme correspond faiblement à certains transporteurs, Peek affiche « Vérification des services de suivi… » pendant qu’il les interroge ; un numéro ne correspondant à aucune forme est recherché une fois ajouté. Si Peek ne nomme pas le transporteur, appuie sur « Changer » et choisis Stone3PL sous « Tous les transporteurs », ou utilise « Changer de transporteur » sur un colis déjà ajouté.

Peek lit le statut global, chaque ligne avec son heure, les pays d’origine et de destination, le service d’expédition, ainsi que le transporteur final et son numéro lorsqu’ils sont indiqués. Il supprime les numéros de téléphone. Stone3PL ne donne ni date de livraison prévue, ni poids, ni lieux de scan, ni preuve de livraison.

Lorsque Stone3PL nomme le transporteur final et donne son numéro, Peek interroge ce transporteur. Avec un numéro postal (deux lettres, neuf chiffres, deux lettres) sans transporteur nommé, Peek interroge la poste du pays de destination, comme [USPS](carrier:usps) aux États-Unis, [Royal Mail](carrier:royal-mail) au Royaume-Uni, An Post en Irlande, [Canada Post](carrier:canada-post) ou [Australia Post](carrier:australia-post). Avec un autre numéro, il interroge le transporteur unique auquel ce format appartient, s’il existe. Dès que ce transporteur affiche un scan au moins aussi récent que le dernier de Stone3PL, Peek suit le colis chez lui en gardant l’historique de Stone3PL. Un transporteur nommé par Stone3PL s’affiche même avant cela (« Livraison par USPS », par exemple) ; tant que ce transporteur n’a que l’annonce sans scan propre, le colis indique « Annoncé à …, pas encore reçu ». Faute d’un tel relais, Peek reste sur le suivi de Stone3PL.

Tant que tu as le colis ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement Stone3PL jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes dès 6 h, heure du pays de destination, le jour où le colis passe en livraison, tant que le suivi de Stone3PL répond ; sinon, et après deux jours sans nouveauté (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures ; après sept jours sans nouveauté sur « Étiquette créée », toutes les 6 heures. Dès qu’un autre transporteur prend le relais, Peek adopte le rythme du transporteur chargé de la livraison. Tu peux choisir les notifications « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas contacter Stone3PL ni le vendeur pour toi.

## Questions sur le suivi Stone3PL

### Stone3PL est-il fiable ?

Stone3PL indique faire partie du groupe Stone, avec son siège à Shenzhen, plus de 10 ans d’expérience et plus de 8 000 clients sous contrat. Elle publie une adresse, un numéro de téléphone et une adresse e-mail. Comme elle travaille pour les vendeurs, les questions sur ta commande vont à la boutique.

### Où suivre un colis Stone3PL ?

Sur [la page de suivi de Stone3PL](https://stone3pl.com/?route=services/track) ou dans Peek. Si le résultat de Stone3PL indique le transporteur qui livre dans ton pays, tu peux aussi suivre ce numéro sur le site de ce transporteur.

### Que signifie « Pick up by local carrier at destination port » ?

Le transporteur qui livre dans ton pays a récupéré ton colis au port ou à l’aéroport d’arrivée. Cela ne veut pas dire que tu dois aller le chercher.

### Combien de temps Stone3PL met-il pour livrer ?

Stone3PL ne publie aucun délai de livraison. Fie-toi à l’estimation du vendeur et contacte-le si le colis est en retard.

### Comment contacter Stone3PL ?

Par e-mail à support@stone3pl.com, ou par téléphone au 86-0755-81466633 (numéro à Shenzhen, Chine). Pour ta commande, adresse-toi d’abord au vendeur.

:::sources
- [Stone3PL: Home](https://stone3pl.com/) – logistique e-commerce depuis la Chine vers le monde pour vendeurs, dédouanement DDP et Delivered Duty Paid, service retour et réexpédition, entrepôts en NORAM, APAC et EMEA
- [Stone3PL: Logistics tracking](https://stone3pl.com/?route=services/track) – outil en anglais, jusqu’à 40 numéros un par ligne, lettres, chiffres et tirets en majuscules, transporteurs suivis
- [Stone3PL: About us](https://stone3pl.com/index.php?route=services/about_us) – groupe Stone, siège à Shenzhen, plus de 10 ans d’expérience, plus de 8 000 clients sous contrat, partenaires logistiques mondiaux
- [Stone3PL: Contact us](https://stone3pl.com/index.php?route=services/contact_us) – siège à Shenzhen, e-mail, téléphone, adresse
- [Stone3PL: Privacy Policy](https://stone3pl.com/index.php?route=services/privacy) – adresse e-mail d’assistance
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de douane de 3 € par article jusqu’à 150 € depuis le 1er juillet 2026, frais de traitement de l’Union de 2 € depuis le 1er novembre 2026
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – 2 € par article quelle que soit la valeur, définition d’un article
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – format du numéro, libellés et étapes, statut global, heure de Chine, transporteur final et relais
:::
