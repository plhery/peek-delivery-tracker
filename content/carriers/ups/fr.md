---
title: UPS tracking : suivi de colis, statuts et livraison
description: Suivre un colis UPS : à quoi ressemble le numéro 1Z, ce que veulent dire Exception et les autres scans, Access Point, frais de douane et tes droits en cas de perte.
slug: ups-tracking-suivi-colis
published: 2026-10-13
updated: 2026-10-27
---

Les numéros de suivi UPS (United Parcel Service) commencent en général par `1Z`. Pour suivre ton colis, colle le numéro ou un lien de suivi UPS dans le champ ci-dessus ; la page de suivi de UPS elle-même se trouve sur ups.com.

## Numéro de suivi UPS : où le trouver et à quoi il ressemble

C'est la boutique qui t'envoie le numéro, en général dans l'e-mail d'expédition ou sur la page de ta commande. Un lien de suivi ups.com le contient aussi, après `tracknum=`. Toujours rien ? Voir [où trouver son numéro de suivi](guide:find-tracking-number).

| Forme (inventée) | Ce que c'est |
| --- | --- |
| `1Z1234567812345674` | Le numéro de colis UPS : `1Z` puis 16 lettres et chiffres, 18 caractères en tout. Le dernier chiffre est une clé de contrôle calculée sur les 15 caractères qui le précèdent. |
| `H1234567890` | Une lettre (H, J, K ou V) et 10 chiffres. Ça peut être un numéro UPS, mais d'autres transporteurs utilisent la même forme. |

## Statuts du suivi UPS : « Exception », « Out for Delivery » et les autres

La page de suivi anglaise de UPS affiche une barre de progression (`Label Created`, `We Have Your Package`, `On the Way`, `Out for Delivery`, `Delivered`) et, en dessous, les scans. Peek lit ces scans en anglais et affiche à côté le statut en français. Voici les principaux, à peu près dans l'ordre où un colis les rencontre :

| UPS écrit | Peek affiche | Ce que ça veut dire pour toi |
| --- | --- | --- |
| `Shipper created a label, UPS has not received the package yet.` | Étiquette créée | L'expéditeur a créé une étiquette. UPS n'a pas encore le colis. |
| `Drop-Off`, `Pickup Scan` | Pris en charge | UPS a le colis : l'expéditeur l'a déposé dans un point Access Point, ou UPS est venu le chercher. Le premier `Arrived at Facility` compte aussi ici. |
| `Arrived at Facility`, `Departed from Facility`, `Processing at UPS Facility` | En transit | Il circule dans le réseau de UPS. `Export Scan` et `Import Scan` marquent le passage d'une frontière. |
| `Your package is prepared for clearance.` | À la douane | Il passe la douane. |
| `Duties or taxes are due on this package.` | À la douane | Tu dois payer des frais d'importation avant que UPS livre (voir plus bas). |
| `Loaded on Delivery Vehicle`, `Out For Delivery Today` | En livraison | Un livreur l'a avec lui. |
| `The receiver was not available at the time of the final delivery attempt.` | Tentative de livraison | La dernière tentative a échoué. |
| `Delivered to UPS Access Point™`, `UPS Access Point™ possession` | Prêt à être retiré | Il t'attend dans un point UPS Access Point. |
| `DELIVERED` | Livré | Livré, ou retiré par toi dans un point Access Point. |
| `Return to Sender` | Retourné à l’expéditeur | Il repart chez l'expéditeur. |

`Exception` est le statut générique de UPS, et il peut ne signaler qu'un simple retard. Lis le scan en dessous : `We're sorry this package may experience a temporary delay.` annonce juste un retard, alors que `The company or receiver name is incorrect. This will delay delivery.` veut dire que la livraison attend tes coordonnées (Peek affiche alors « Problème signalé »). Peek se fie au scan le plus récent, pas au statut générique.

## Livraison UPS manquée et point Access Point

Quand le livreur ne peut pas livrer, le scan indique que le destinataire était absent ou l'entreprise fermée, et UPS fait une nouvelle tentative. Après la dernière, un autre scan dit la suite : UPS garde le colis, le dépose dans un point Access Point ou le renvoie à l'expéditeur. Plus d'infos dans [avis de passage : que faire](guide:missed-delivery).

UPS peut aussi déposer le colis dans un point UPS Access Point. Une fois qu'il y est, les scans indiquent `Delivered to UPS Access Point™`, puis `UPS Access Point™ possession`, et Peek affiche le nom et l'adresse du point. Ne traîne pas : quand un colis non retiré repart, le scan indique `The UPS Access Point™ location has prepared the package for return to UPS or pickup by UPS.` Si un point Access Point ferme, UPS récupère ses colis et le scan annonce une livraison dans un autre point. Voir [colis non retiré](guide:uncollected-parcel).

## Frais de douane sur un colis UPS

Un colis venu de l'extérieur de l'UE, ou de l'étranger pour la Suisse, peut demander des frais d'importation avant que UPS le livre. Le suivi affiche alors `Duties or taxes are due on this package.` et `Your package is being held until duties and taxes are paid. Awaiting customer action.`

- **France et Belgique** : comme partout dans l'UE depuis le 1er juillet 2026, une commande de 150 € ou moins venue de l'extérieur de l'UE paie un droit de douane forfaitaire de 3 € par article (les articles semblables comptent ensemble), en plus de la TVA ; c'est le vendeur ou le transporteur qui le perçoit. En France métropolitaine, la douane précise que la TVA est due dès le premier euro et que le transporteur peut te demander ces taxes à la réception, en y ajoutant ses propres frais de dédouanement, sans lien avec ce que perçoit la douane.
- **Suisse** : selon l'OFDF, dans le trafic postal et de courrier rapide, c'est le transporteur (UPS, La Poste, etc.) qui dédouane, et lui seul peut t'aider à rechercher ton envoi. Il perçoit des frais de dédouanement pour ce travail, en général facturés au destinataire avec les droits de douane et la TVA ; pour leur montant, renseigne-toi auprès de lui.

Plus d'infos dans [colis bloqué en douane](guide:customs).

## Colis UPS bloqué, en retard, perdu ou abîmé

Si le suivi ne bouge plus, lis [suivi de colis qui ne bouge plus](guide:tracking-not-updating).

Si tu as acheté dans une boutique, c'est à elle de te faire parvenir le colis, même quand UPS le transporte : dans l'UE, selon Your Europe, le commerçant répond des dommages subis par la marchandise de l'expédition jusqu'à la réception.

- **France** : selon Service-Public.fr, quand la livraison passe par le transporteur proposé par le vendeur, le vendeur est seul responsable : s'il ne peut pas prouver que tu as reçu le produit et que tu le contestes, la perte est pour lui. Sans date convenue, il doit livrer au plus tard 30 jours après la commande. Passé ce délai, demande-lui par lettre recommandée avec avis de réception ou par e-mail de livrer dans un délai supplémentaire raisonnable. S'il ne livre toujours pas, tu peux annuler la commande, et il doit te rembourser tout ce que tu as payé sous 14 jours.
- **Belgique** : le SPF Économie conseille de contacter le vendeur, pas l'entreprise de livraison. Si la livraison dépasse largement le délai convenu, tu peux le mettre en demeure, de préférence par e-mail ou lettre recommandée. Le livreur ne peut signer l'accusé de réception à ta place et laisser le colis devant ta porte que si tu l'as autorisé ; sinon, préviens le vendeur et le livreur et conteste l'accusé de réception. En cas de litige, adresse-toi au Service de médiation pour le secteur postal.

Marqué `DELIVERED` mais introuvable ? La page de UPS peut indiquer où le colis a été laissé (par exemple `Left at: Front Door`) et donner une preuve de livraison, que Peek ne conserve pas. Vérifie sur ups.com, puis contacte la boutique. En France, si le vendeur affirme avoir livré, Service-Public.fr dit de le mettre en demeure de t'envoyer le colis, par lettre recommandée avec accusé de réception. Plus d'infos dans [colis livré mais non reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel).

## Suivre un colis UPS avec Peek

[Peek](/) reconnaît UPS tout de suite pour un numéro `1Z` dont la clé de contrôle est bonne. Si elle est fausse, seul UPS est proposé, et UPS refuse le numéro comme mal saisi. Tu peux aussi coller un lien ups.com, ou n'importe quel lien qui contient un numéro `1Z`. Peek n'interroge UPS qu'avec des numéros `1Z` : les autres formes, comme `H1234567890`, ne sont suivies que par des services de suivi, même après « Changer de transporteur ».

Pour lire le suivi, le serveur de Peek ouvre la page de suivi de ups.com dans un navigateur, toutes les 10 minutes en journée, toutes les 5 minutes une fois le colis en livraison, et toutes les une à deux heures la nuit. Si UPS refuse, ou si seul le statut est lisible, des services de suivi complètent l'historique. Tu vois le statut, chaque scan sur une carte avec son lieu et son heure exacte, le jour de livraison prévu (jamais un créneau horaire), l'heure de livraison, le pays de destination et, tant que le colis attend, le nom et l'adresse du point Access Point. Peek peut t'envoyer des notifications : « Toutes les mises à jour », « Importantes uniquement » ou « Jour de livraison uniquement ».

Peek ne reprend ni le nom de la personne qui a signé, ni ton adresse, ni la preuve de livraison, ni l'endroit où le colis a été déposé, ni les horaires du point Access Point. Il n'affiche pas la photo de livraison, et UPS ne lui donne ni le poids ni l'expéditeur. Les scans apparaissent en anglais, tels que UPS les écrit, quelle que soit ta langue : une livraison se lit « DELIVERED ». Aux États-Unis, quand UPS confie un colis Ground Saver à la poste américaine et donne son numéro, Peek suit la suite chez USPS (« Livraison par USPS »).

## Questions sur le suivi UPS

### Pourquoi mon numéro de suivi UPS est-il refusé ?

Le plus souvent, il est mal saisi : le dernier caractère d'un numéro `1Z` est une clé de contrôle, une seule erreur la rend en général fausse, et UPS refuse le numéro. Il peut aussi appartenir à un autre transporteur : regarde lequel l'e-mail de la boutique nomme.

### Au bout de combien de temps le suivi UPS s'affiche-t-il ?

Le premier scan, `Shipper created a label, UPS has not received the package yet.`, apparaît dès que l'expéditeur a créé l'étiquette. Le suivi n'avance qu'une fois que l'expéditeur a déposé le colis ou que UPS est venu le chercher (`Drop-Off`, `Pickup Scan`).

### Puis-je modifier une livraison UPS dans Peek ?

Non : Peek ne fait que lire le suivi de UPS. Les changements que tu fais auprès de UPS apparaissent dans les scans, par exemple `The package will be delivered to the UPS Access Point™ location requested by the receiver.`

### Un SMS de UPS, c'est une arnaque ?

Ça peut l'être. Cybermalveillance.gouv.fr signale de faux SMS et e-mails au nom de transporteurs, UPS compris, qui annoncent un problème de livraison ou des frais à payer (frais de port, TVA, frais de douane) et renvoient vers un faux site qui demande ta carte bancaire. Ne clique pas sur le lien : vérifie le vrai suivi toi-même, sur ups.com ou dans Peek, ou contacte directement le transporteur. De vrais frais d'importation apparaissent dans le suivi, sous forme de scans de douane. En France, signale le SMS au 33700 (gratuit). Plus d'infos dans [arnaque colis par SMS](guide:delivery-scam-texts).

:::sources
- [Douane : Droit de douane forfaitaire de 3 euros sur les ventes à distance de biens importés](https://www.douane.gouv.fr/fiche/droit-de-douane-forfaitaire-de-3-euros-sur-les-ventes-distance-de-biens-importes) – 3 € par article jusqu'à 150 €, règlement de l'UE en vigueur depuis le 1er juillet 2026
- [Douane : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA dès le premier euro hors UE, taxes demandées par le transporteur, ses frais de dédouanement
- [Service-Public.fr : Achat à distance : livraison du bien ou exécution de la prestation](https://www.service-public.gouv.fr/particuliers/vosdroits/F10037) – vendeur seul responsable, preuve de réception, 30 jours, délai supplémentaire, annulation, remboursement sous 14 jours, mise en demeure
- [SPF Économie : Achats en ligne : voici vos droits !](https://news.economie.fgov.be/245209-achats-en-ligne-voici-vos-droits/) – contacter le vendeur, mise en demeure, accusé de réception contesté, Service de médiation pour le secteur postal (Belgique)
- [Your Europe : Expédition et livraison](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_fr.htm) – 3 € par article dans l'UE, perçu par le vendeur ou le transporteur, responsabilité du commerçant jusqu'à la réception
- [OFDF : Frais de dédouanement et suivi des colis](https://www.bazg.admin.ch/fr/frais-dedouanement-et-suivi-des-colis) – dédouanement par le transporteur (UPS, La Poste…), recherche de l'envoi, frais de dédouanement (Suisse)
- [OFDF : Achats sur Internet, envois postaux et par coursier](https://www.bazg.admin.ch/fr/achat-internet-envois-postaux-importation-suisse-cout-douane-tva) – frais du transporteur facturés au destinataire avec les droits et la TVA
- [Cybermalveillance.gouv.fr : L'hameçonnage à la livraison de colis](https://www.cybermalveillance.gouv.fr/tous-nos-contenus/actualites/escroqueries-livraison-colis) – faux SMS au nom de transporteurs dont UPS, vérifier le vrai suivi, 33700
- [Universal Parcel Scraper : UPS](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ups) – formes de numéro, clé de contrôle, scans de UPS, ce que Peek lit chez UPS
:::
