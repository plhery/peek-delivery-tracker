---
title: Suivi GOFO Express : numéro GFUS, statuts et aide
description: Suis un colis GOFO Express aux États-Unis : formats GFUS, GF, CR et MGP, sens des statuts anglais, délais légaux en cas de retard et suivi dans Peek.
slug: gofo-express-suivi
published: 2026-11-17
updated: 2026-11-17
---

GOFO Express est un transporteur du dernier kilomètre qui livre des colis aux États-Unis : il récupère les envois à l'entrepôt de l'expéditeur ou auprès d'un partenaire logistique, les fait transiter par ses centres de tri et ses hubs régionaux jusqu'à l'agence locale de livraison, puis les confie à un chauffeur. Que tu fasses livrer une commande internationale à une adresse américaine ou que tu suives un achat expédié vers les États-Unis, colle ton numéro de suivi dans le champ ci-dessus ; GOFO dispose aussi de son propre outil de suivi sur son site. Le numéro t'est transmis par la boutique qui a expédié ta commande. Si tu ne le retrouves pas, consulte [comment retrouver un numéro de suivi](guide:find-tracking-number).

## Numéro de suivi GOFO Express : formats reconnus

GOFO Express utilise quatre séries de numéros aux États-Unis. Les voici avec des chiffres inventés, ainsi que deux formats partenaires que l'on rencontre sur les mêmes colis :

| Numéro (inventé) | Ce que c'est |
| --- | --- |
| `GFUS12345678901234` | `GFUS` suivi de 14 chiffres : le numéro de bordereau (*waybill*) principal de GOFO aux États-Unis. Si une lettre `O` remplace le premier zéro juste après `GFUS`, Peek la lit automatiquement comme `0`. |
| `GF1234567890123` | `GF` suivi de 13 chiffres : une série américaine plus ancienne que le suivi de GOFO prend toujours en charge. |
| `CR123456789012` | `CR` suivi de 12 chiffres : autre série américaine de GOFO, parfois associée à un bordereau `GFUS`. |
| `MGP1234567890123` | `MGP` suivi de 13 chiffres : numéro de suivi secondaire enregistré par GOFO sur certains bordereaux `GFUS`. |
| `FC1234567890123` | `FC` suivi de 13 chiffres : numéro propre à [DHLink](carrier:dhlink) sur les commandes DHgate remises à GOFO aux États-Unis. |
| `YT1234567890123456` | `YT` suivi de 16 chiffres : numéro de [YunExpress](carrier:yunexpress), même lorsque GOFO assure la livraison finale aux États-Unis. |

**Deux numéros pour un même colis.** Certains envois portent à la fois un bordereau `GFUS` et un numéro de suivi `CR` (12 chiffres) ou `MGP` (13 chiffres) : l'un comme l'autre renvoie exactement au même colis dans Peek et sur le suivi américain de GOFO. Sur les commandes DHgate confiées à GOFO par [DHLink](carrier:dhlink), le numéro de suivi est celui de DHLink, `FC` suivi de 13 chiffres : Peek interroge DHLink, et le suivi américain de GOFO retrouve aussi le colis avec ce numéro `FC`.

**Numéros `YT` de YunExpress.** Quand GOFO livre aux États-Unis un colis acheminé par [YunExpress](carrier:yunexpress), le suivi de YunExpress affiche également les scans de GOFO, et le site américain de GOFO accepte le même numéro `YT`.

**Ne pas confondre avec GOFO France et les autres réseaux nationaux.** Le groupe GOFO exploite des réseaux distincts selon les pays, chacun avec ses propres préfixes : `GFFR` ou `CIFR` (ainsi que certains numéros en `PK` à 20 chiffres) relèvent de [GOFO France](carrier:gofo-fr), `GFCA` de GOFO Canada, `GFIT` ou `CIIT` de GOFO Italy, `GFES` ou `CIES` de GOFO Spain, et `GFNL` ou `CINL` de GOFO Netherlands. Peek les distingue automatiquement et interroge le réseau du bon pays.

## Statuts de suivi GOFO Express : signification des scans anglais

Les scans renvoyés par GOFO Express aux États-Unis sont rédigés en anglais. Voici ceux que ton colis peut traverser dans l'ordre approximatif du trajet (tous les colis ne passent pas par chaque étape) :

| GOFO écrit | Ce que cela veut dire pour toi |
| --- | --- |
| `Shipping Label Created` | L'expéditeur a créé l'étiquette d'envoi ; GOFO n'a pas encore le colis en main. |
| `Pick Up Start` | GOFO est parti récupérer le colis chez l'expéditeur, sans l'avoir encore pris en charge. |
| `Pickup Exception, GOFO has not Received the Package from the Warehouse yet.` | GOFO n'a toujours pas reçu le colis depuis l'entrepôt du vendeur. |
| `Picked up by Shipping Partner, GOFO Awaiting Item` | Un partenaire d'expédition a collecté le colis pour le compte de GOFO, qui attend son arrivée au centre de tri. |
| `Pick Up Success` | GOFO a récupéré le colis auprès de l'expéditeur. |
| `Arrived at GOFO Regional Origin Hub` | Premier scan physique du colis dans un hub régional d'origine de GOFO. |
| `Arrived at Sorting Center` | Arrivée dans un centre de tri, avant le passage en hub régional. |
| `Arrived at GOFO Regional Hub` | Arrivée dans un hub régional de GOFO sur le parcours. |
| `Processing at GOFO Facility` | Tri en cours dans une installation de GOFO. |
| `Departed GOFO Regional Hub` | Départ du hub régional vers l'étape suivante. |
| `Loaded on Vehicle` | Chargé dans un véhicule de liaison vers le prochain site. |
| `In Transit to Next Facility` | En route entre deux installations de GOFO. |
| `Arrived at GOFO Delivery Station` | Arrivée dans l'agence locale GOFO chargée de la tournée finale. |
| `Departed GOFO Delivery Station` | Sortie de l'agence locale de livraison. |
| `Out for Delivery` | Le chauffeur est en tournée avec ton colis. |
| `Delivered` | Colis livré à destination. |
| `Delivery Exception, Business Closed` | Tentative de livraison échouée parce que l'entreprise ou le local était fermé (le motif après la virgule varie selon l'incident). |
| `Package Returned to GOFO Facility` | Le lendemain d'une tentative échouée, le colis est revenu à l'agence locale de GOFO en vue d'un nouvel essai — ce n'est pas un retour à l'expéditeur. |
| `Incorrect/Insufficient Address, Additional Details Required for Delivery.` | Adresse erronée ou incomplète signalée à l'arrivée au hub ou après un échec de livraison ; le colis attend un complément d'adresse. |
| `Address Corrected.` | L'adresse a été corrigée et le colis reprend sa route. |
| `Label Cancelled` | L'étiquette a été annulée après sa création : aucun colis ne viendra sous ce numéro. |

Quand GOFO écrit `Delivery Exception, …`, Peek place le colis à l'étape « Tentative de livraison » et affiche la phrase complète de GOFO sous « Ce que dit GOFO Express ». Si le scan suivant indique `Package Returned to GOFO Facility`, le colis repasse « En transit » dans l'agence locale pour une nouvelle mise en tournée (et non « Retourné à l’expéditeur »). En revanche, `Incorrect/Insufficient Address…` et `Label Cancelled` déclenchent tous deux l'étape « Problème signalé » : pour le premier, préviens aussitôt le vendeur afin de compléter le code d'accès, le numéro d'appartement ou la rue ; pour le second, demande-lui le nouveau numéro d'expédition. Sur certains scans, GOFO ajoute en fin de ligne ses coordonnées d'assistance en anglais ou en espagnol : Peek retire cette mention répétitive pour ne garder que l'événement.

**Fuseaux horaires.** GOFO horodate toujours `Shipping Label Created` sur l'heure du Pacifique (`America/Los_Angeles`) et associe chaque scan suivant au fuseau américain de l'installation concernée. Peek convertit chaque horaire dans le fuseau de ton téléphone ou de ton ordinateur (« Heures dans ton fuseau horaire : … ») au format 24 heures : une étiquette créée à 22 h 00 sur la côte Ouest des États-Unis apparaît ainsi à 07 h 00 le lendemain matin si tu consultes le suivi depuis Paris ou Bruxelles (06 h 00 pendant les quelques semaines de mars et de fin octobre où les États-Unis et l'Europe ne changent pas d'heure le même jour).

## Colis GOFO Express bloqué, en retard ou introuvable : quoi faire

**Bloqué sur `Shipping Label Created`, `Pick Up Start` ou `Pickup Exception`.** Tant que l'un de ces trois libellés reste le dernier événement, GOFO n'a pas encore reçu physiquement la marchandise de l'entrepôt du vendeur. Si rien ne bouge au bout de quelques jours, demande à la boutique quand la remise aura lieu. Plus d'explications dans [pourquoi un suivi ne bouge plus](guide:tracking-not-updating).

**Plus de nouveau scan en cours de route.** Si quatre jours s'écoulent sans nouveau scan avant la mise en tournée, préviens le vendeur ou utilise le lien d'assistance que Peek fait alors apparaître sur la fiche du colis.

**Échec de livraison ou problème d'adresse.** Lis le motif inscrit après la virgule dans `Delivery Exception, …` ou le message `Incorrect/Insufficient Address…`. S'il manque un numéro d'appartement, un digicode ou des horaires d'ouverture professionnels, transmets-les rapidement au vendeur pour que l'adresse passe à `Address Corrected.`.

**Indiqué comme livré, mais introuvable.** Peek n'importe pas les photos de preuve de livraison que GOFO conserve sur son propre site. Vérifie d'abord le porche, le local courrier de l'immeuble (*mailroom*), l'accueil de la résidence ou la page de suivi de GOFO pour voir si une photo de dépôt y figure, puis signale l'absence au vendeur. Voir aussi [colis livré mais non reçu](guide:delivered-not-received).

**Colis perdu, retourné ou droits de l'acheteur.** C'est toujours le vendeur qui a le contrat de transport avec GOFO : demande-lui un renvoi ou un remboursement (voir [colis perdu](guide:lost-parcel) et [colis retourné à l'expéditeur](guide:returned-to-sender)). Aux États-Unis, la règle fédérale de la FTC sur les ventes à distance ([16 CFR § 435.2](https://www.law.cornell.edu/cfr/text/16/435.2)) oblige le marchand à expédier dans le délai annoncé — ou dans les 30 jours si aucun délai n'était précisé — et, en cas de retard, à solliciter ton accord ou à te rembourser sans délai. Pour un paiement par carte de crédit américaine portant sur un bien jamais livré, le Fair Credit Billing Act ([15 U.S.C. § 1666](https://www.law.cornell.edu/uscode/text/15/1666)) prévoit un délai de 60 jours après l'envoi du relevé pour contester par écrit l'erreur de facturation auprès de l'émetteur. Si tu as acheté auprès d'un vendeur professionnel établi dans l'Union européenne, le délai légal par défaut de livraison est également de 30 jours ([Your Europe](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm)). Enfin, la loi fédérale ([39 U.S.C. § 3009](https://www.law.cornell.edu/uscode/text/39/3009)) autorise le destinataire d'une marchandise non commandée envoyée par la poste à la considérer comme un cadeau sans rien payer ni la renvoyer ; ce texte vise les envois postaux, pas expressément un colis livré par GOFO.

## Contacter le service client GOFO Express

Pour tout retard, changement d'adresse ou demande de remboursement, adresse-toi d'abord à la boutique en ligne auprès de laquelle tu as passé commande. Si ton colis reste quatre jours sans nouveau scan avant d'être mis en livraison (même s'il n'a encore que l'étiquette), Peek affiche sur sa fiche le bouton « Demande de l’aide à GOFO Express », qui renvoie vers la page américaine d'assistance de GOFO (`gofo.com/us/customer-service`).

## Suivre un colis GOFO Express avec Peek

Avec un numéro `GFUS` à 14 chiffres, [Peek](/) reconnaît immédiatement GOFO Express (« Transporteur détecté »). Avec un numéro `GF` (13 chiffres), `CR` (12 chiffres) ou `MGP` (13 chiffres), Peek interroge GOFO en direct (« Vérification des services de suivi… ») ; dès que GOFO confirme l'envoi, tu vois « GOFO Express a ce colis. » et le suivi commence. Les espaces éventuels dans le numéro sont ignorés. Comme [Cainiao](carrier:cainiao) suit certaines commandes AliExpress destinées aux États-Unis sous le bordereau `GFUS` de GOFO, Peek consulte aussi Cainiao pour les étapes amont lorsque GOFO ne montre que l'étiquette depuis deux jours ; plus de détails dans [suivre une commande AliExpress](guide:aliexpress-orders).

Tant que GOFO ne connaît pas encore un numéro `GF`, `CR` ou `MGP`, Peek affiche « En attente du premier scan de GOFO Express » (tu peux aussi fixer GOFO Express manuellement via « Changer »). Lorsqu'un bordereau est déjà enregistré chez GOFO sans premier scan, ou pour un numéro `GFUS` ajouté sur peektracker.com, tu vois « En attente de GOFO Express » et Peek continue de vérifier tant que GOFO garde le bordereau en attente. Ce n'est que si GOFO renvoie le numéro comme inconnu pendant deux jours sur un colis enregistré chez GOFO Express, sans réponse d'aucune autre source, que peektracker.com affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. » et espace alors les vérifications à une fois par jour, puis une fois par semaine au bout de sept jours. Tu peux également coller un lien de suivi américain de `gofo.com` (`/us…` ou racine) ou de `gofoexpress.com` contenant le paramètre `searchID=`.

Peek affiche chaque scan avec la ville et l'État américain indiqués par GOFO (par exemple `Jamaica, NY`), place ces étapes sur une carte, indique l'heure de livraison ainsi que le pays de destination. Les libellés des scans restent en anglais tels que GOFO les transmet, tandis que l'étape principale s'affiche en français (« Étiquette créée », « Pris en charge », « En transit », « En livraison », « Livré » — sans le détail que GOFO ajoute parfois après la virgule). GOFO fournit une date de livraison estimée que Peek affiche jusqu'à ce que le colis passe « En livraison », « Livré » ou « Retourné à l’expéditeur » ; en revanche, Peek écarte les photos de livraison, les coordonnées GPS et le poids.

Peek interroge GOFO toutes les 30 minutes, y compris pendant que le colis est « En livraison », attend 60 minutes après un échec de connexion, et passe à un contrôle toutes les heures après deux jours sans nouveauté (ou après 12 heures si le colis reste sur « Étiquette créée » ou « Pris en charge »). Tu peux activer des notifications sur « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni GOFO ni le vendeur à ta place.

## Questions fréquentes sur le suivi GOFO Express

### Qu'est-ce que GOFO Express ?

C'est un transporteur de colis qui assure la collecte en entrepôt, le tri interrégional et la livraison du dernier kilomètre aux États-Unis. Le groupe exploite par ailleurs des réseaux nationaux distincts en France ([GOFO France](carrier:gofo-fr)), au Canada, en Italie, en Espagne et aux Pays-Bas, chacun avec sa propre numérotation.

### Pourquoi mon numéro de suivi GOFO Express ne fonctionne-t-il pas ?

Vérifie d'abord qu'il s'agit bien d'un format américain de GOFO : `GFUS` (14 chiffres), `GF` (13 chiffres), `CR` (12 chiffres) ou `MGP` (13 chiffres). Les préfixes `GFFR`/`CIFR` sont ceux de [GOFO France](carrier:gofo-fr), `GFCA`, `GFIT`/`CIIT`, `GFES`/`CIES` et `GFNL`/`CINL` ceux des autres réseaux nationaux, et `YT` celui de [YunExpress](carrier:yunexpress) ; un colis DHgate livré par GOFO peut aussi être cherché avec son numéro `FC` de [DHLink](carrier:dhlink). Si le vendeur vient de créer l'envoi, le bordereau peut être simplement en attente du premier scan. Si GOFO rejette toujours le numéro comme inconnu au bout de deux jours sur peektracker.com, vérifie s'il y a une faute de frappe ; et si le suivi affiche `Label Cancelled`, l'étiquette a été annulée et tu dois demander le nouveau numéro au marchand.

### Que veut dire `Package Returned to GOFO Facility` chez GOFO ?

Contrairement à ce que la traduction littérale peut laisser croire, ce statut ne signifie pas que le colis repart chez le vendeur : il apparaît le lendemain d'un échec de livraison (`Delivery Exception`) quand le chauffeur a rapporté le colis à l'agence locale de GOFO en vue d'une nouvelle tentative, et Peek le maintient donc « En transit ».

### Pourquoi ma commande AliExpress ou DHgate a-t-elle un numéro GOFO ?

Pour les livraisons aux États-Unis, [Cainiao](carrier:cainiao) (la logistique d'AliExpress) suit certaines de ces commandes sous le numéro `GFUS` de GOFO Express quand GOFO les livre, tandis que [DHLink](carrier:dhlink) confie certains colis DHgate à GOFO, qui les suit aussi avec le numéro `FC` de DHLink. Avec un numéro `GFUS`, Peek interroge GOFO et consulte aussi Cainiao tant que GOFO n'affiche que l'étiquette depuis deux jours.

:::sources
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats américains GFUS, GF, CR et MGP, numéros couplés DHLink (FC) et YunExpress (YT), réseaux nationaux GOFO, scans et étapes, fuseaux horaires, suppression de la ligne support, Cainiao et champs ignorés par Peek
- [16 CFR § 435.2 – Mail, Internet, or Telephone Order Merchandise Rule (Cornell LII)](https://www.law.cornell.edu/cfr/text/16/435.2) – obligation d'expédition dans le délai promis ou sous 30 jours aux États-Unis et droit au remboursement en cas de retard
- [15 U.S.C. § 1666 – Fair Credit Billing Act (Cornell LII)](https://www.law.cornell.edu/uscode/text/15/1666) – contestation écrite sous 60 jours d'une erreur de facturation carte pour marchandise non livrée
- [39 U.S.C. § 3009 – Mailing of unordered merchandise (Cornell LII)](https://www.law.cornell.edu/uscode/text/39/3009) – envois non sollicités livrés aux États-Unis réputés cadeaux sans obligation de paiement
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – délai légal par défaut de 30 jours lors d'un achat auprès d'un vendeur établi dans l'UE
:::
