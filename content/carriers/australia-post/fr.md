---
title: Suivi Australia Post : numéros, statuts et consignes
description: Suivre un colis Australia Post : où trouver le numéro d'article, sens des statuts en anglais, retrait en bureau de poste ou Parcel Locker, retards et contact.
slug: suivi-australia-post
published: 2026-11-21
updated: 2026-11-21
---

Australia Post est l'opérateur postal national de l'**Australie**. Tu peux suivre un envoi sans compte sur [auspost.com.au/mypost/track](https://auspost.com.au/mypost/track), dans l'application AusPost ou en collant directement le numéro dans le champ ci-dessus. Ce code provient de l'expéditeur : confirmation de commande de la boutique, e-mail d'Australia Post ou reçu remis au guichet d'un bureau de poste australien.

## Numéro de suivi Australia Post : où le trouver et formats

Australia Post l'appelle le numéro de suivi ou identifiant d'article (*tracking number, or article ID*). Il figure selon le cas :

- **Achat en ligne :** dans la confirmation de commande ou la facture du marchand ; si l'expéditeur a transmis ton adresse e-mail à Australia Post, celle-ci t'envoie directement le numéro et les étapes.
- **Dépôt en bureau de poste en Australie :** sur le reçu d'affranchissement.
- **Pochette prépayée (*prepaid satchel*) :** sous le code-barres et sur la vignette détachable.

Australia Post ne peut pas rechercher un envoi par nom ni par adresse : sans numéro, demande-le à l'expéditeur. Les colis rattachés à l'adresse e-mail ou au mobile d'un compte MyPost s'ajoutent automatiquement dans le compte et l'application. Sur `auspost.com.au/mypost/track`, tu peux saisir jusqu'à 10 numéros à la fois.

Australia Post ne publie pas de liste de formats, mais voici les structures que Peek reconnaît (avec caractères inventés) :

| Format (exemple) | Ce que c'est |
| --- | --- |
| `33X482910573000482910` | Identifiant d'article de 21 caractères : 3 lettres ou chiffres, 9 chiffres, `000` et 6 chiffres. |
| `2933X482910573000482910` | Même identifiant précédé de 2 chiffres (23 caractères). |
| `94004829105736000482910` | 23 chiffres commençant par `9400` avec `000` avant les 6 derniers chiffres : encore Australia Post. |
| `29ABC4829105` | Numéro d'expédition (*consignment number*) à 12 caractères. |
| `ABC4829105` | Numéro d'expédition à 10 caractères. |
| `ABCZ48291057` | Numéro StarTrack à 12 caractères : 3 lettres ou chiffres, `Z` et 8 chiffres. |
| `0048291057364918273643` | Code-barres prépayé de 22 chiffres commençant par `00` à `03` (avec clé GS1). |
| `R482910573649182736491827` | `R` suivi de 24 chiffres (25 caractères). |
| `EE482910576AU` | Envoi international EMS, colis ou lettre suivie : `C`, `E` ou `L`, une lettre, 9 chiffres et `AU`. |
| `RR482910576AU` | Autre numéro postal international terminé par `AU` (par exemple recommandé). |

Codes proches à ne pas confondre : 2 lettres suivies de 10 chiffres (`AB4829105736`) correspondent à Aramex Australia ou Aramex New Zealand, tandis qu'un code de 22 chiffres commençant par `9400` (`9400482910573649182736`) appartient à [USPS](carrier:usps). Si un numéro n'est pas reconnu, il peut comporter une erreur ou ne pas encore avoir été scanné : Australia Post conseille de réessayer au bout de 24 heures. Plus de repères dans [les formats des numéros de suivi](guide:tracking-number-formats) et [comment trouver son numéro de suivi](guide:find-tracking-number).

## Que signifient les statuts de suivi Australia Post

Au-dessus des scans détaillés, Australia Post affiche un jalon récapitulatif en anglais :

| Jalon Australia Post | Ce que cela signifie | Peek affiche |
| --- | --- | --- |
| `Label created by sender` | L'expéditeur a généré l'étiquette ; Australia Post n'a pas encore le colis. | « Étiquette créée » |
| `Pending` | Pas encore scanné dans le réseau d'Australia Post ; une mise à jour intervient généralement sous 24 à 48 heures. | « En attente de Australia Post » (avant le premier scan) |
| `We've got it` | Australia Post a pris le colis en charge. | « Pris en charge » |
| `It's on its way` | Déposé, trié et en acheminement entre centres. | « En transit » |
| `International arrival - awaiting clearance` | Envoi venu de l'étranger : en attente du dédouanement australien. | « À la douane » |
| `Possible delay` | Météo, incident, erreur d'adressage ou douane ralentissent l'envoi, qui reste en route. | « En transit » / « Problème signalé » selon le code |
| `It's coming today` / `Onboard for delivery` | Chargé dans le véhicule pour une livraison aujourd'hui. | « En livraison » |
| `Attempted delivery` | Personne n'était disponible ou l'accès à l'adresse était impossible. | « Tentative de livraison » |
| `Awaiting collection` | En attente de retrait dans le bureau de poste ou le *Parcel Locker* nommé par le scan. | « Prêt à être retiré » |
| `Delivered` / `Delivered - Left in a safe place` | Livré à l'adresse ou déposé dans un endroit sûr (*Safe Drop*). | « Livré » |
| `Returning to sender` | Non retiré à temps, destinataire inconnu, refusé ou endommagé : retour vers l'expéditeur. | « Retourné à l’expéditeur » |

Si `Pending` ou `Ready for processing` persiste plus d'une semaine, demande à l'expéditeur si le colis a bien été confié. En Australie, les colis inter-États transitent par de grands centres de tri comme Redbank (QLD), Chullora (NSW) ou Sunshine West (VIC) : un scan éloigné du départ comme de l'arrivée est normal. Pour un colis venu de l'étranger vers l'Australie, `It's on its way` signifie qu'il est arrivé en Australie et demeure affiché jusqu'au chargement pour la tournée finale.

En Australie, les facteurs livrent habituellement entre 7 h et 17 h du lundi au vendredi ; un colis non remis à 17 h est en principe prioritaire le jour ouvré suivant. Avec `It's coming today`, certaines zones reçoivent un SMS indiquant un créneau de 2 heures.

`Contact sender` signale un renvoi dû à une adresse erronée ou incomplète, ou à un avis de retrait resté sans suite pendant 10 jours ouvrés ; `Returned` confirme que l'envoi a été rendu à l'expéditeur, seul habilité à organiser un nouvel envoi.

## Livraison manquée, retrait en bureau de poste et Parcel Lockers en Australie

Les règles suivantes s'appliquent aux livraisons effectuées en **Australie** :

En l'absence du destinataire et sans lieu sûr autorisé (*Safe Drop*), le colis est déposé dans un bureau de poste voisin ou, si ton numéro de mobile figure sur l'envoi, dans une consigne *Parcel Locker* (avec envoi d'un code d'accès). Les titulaires d'un compte MyPost reçoivent une alerte par e-mail, application ou SMS ; les autres peuvent encore recevoir un avis papier *Sorry We Missed You*. Voir aussi [livraison manquée](guide:missed-delivery).

Un bureau de poste australien conserve le colis **10 jours ouvrés** avant retour à l'expéditeur. Australia Post ne propose pas de seconde livraison à domicile depuis un bureau de poste ; le transfert vers un autre bureau coûte **7,55 AU$**, payables au retrait (le formulaire de transfert affiche encore 7,20 AU$), et si tu ignores pourquoi le colis a atterri dans ce bureau, tu peux soumettre un signalement de livraison pour demander une dispense de frais. Présente une pièce d'identité avec photo, ou deux pièces sans photo dont l'une porte ton adresse. Un tiers peut retirer l'envoi avec une procuration (application, formulaire ou avis papier) et sa propre pièce d'identité.

Les consignes *Parcel Lockers* sont gratuites et, sur la plupart des sites, accessibles 24 h/24 et 7 j/7. Tu disposes de **2 jours ouvrés** (48 heures hors week-ends et jours fériés) pour retirer le colis avec le code reçu ou le QR code de l'application ; passé ce délai (avec un rappel par e-mail 24 heures avant), le colis est transféré vers un bureau de poste voisin où il reste 10 jours ouvrés.

Avec un compte gratuit MyPost, tu peux rediriger un colis rattaché vers une autre adresse tant qu'il n'est pas en tournée (prévois un jour ouvré supplémentaire) et activer *Safe Drop* jusqu'à une heure avant le passage.

## Suivi bloqué, retard ou colis marqué livré mais absent

L'absence de nouveau scan pendant quelques jours ne veut pas dire que le colis est arrêté : Australia Post indique qu'il continue d'avancer et que la plupart des envois retardés arrivent dans les **5 jours ouvrés** suivant la date prévue. Voir [suivi qui ne bouge plus](guide:tracking-not-updating).

Australia Post oriente d'abord le destinataire vers l'expéditeur afin de vérifier le numéro et l'adresse et de lui laisser ouvrir une réclamation. À défaut, tu peux remplir le formulaire d'objet en retard ou manquant (compte gratuit MyPost requis) une fois passés **10 jours ouvrés** après la date attendue. En Australie, les colis venus de l'étranger peuvent passer jusqu'à **15 jours** à la douane australienne — davantage si des droits sont dus — et les autorités douanières ou de biosécurité écrivent directement au destinataire en cas de retenue prolongée ; une enquête sur un retard international s'ouvre auprès de l'opérateur postal d'origine par l'expéditeur. Voir aussi [colis retenu en douane](guide:customs).

**Marqué livré mais introuvable.** Inspecte les abris autour du domicile, interroge les membres du foyer ou la conciergerie, vérifie la photo de livraison dans l'application ou via le formulaire en ligne, puis dépose une réclamation ; si la photo atteste une remise à ton adresse et que le colis a disparu, signale le vol à la police. En cas d'avarie en Australie, présente le colis et son emballage dans un bureau de poste habilité à constater les dommages. La plupart des services nationaux incluent jusqu'à **100 AU$** d'indemnisation pour perte ou avarie (versée en général à l'expéditeur en cas de perte nationale et au destinataire en cas d'avarie), dans un délai de **6 mois** à compter du dépôt ; pour un achat en ligne, contacte d'abord le vendeur. Voir [colis livré mais non reçu](guide:delivered-not-received).

## Contacter Australia Post et aide

Canaux publiés par Australia Post :

- **Téléphone :** `13 76 78` (`13POST`) depuis l'Australie ou `+61 3 8340 7239` depuis l'étranger, de 8 h à 18 h du lundi au vendredi hors jours fériés nationaux australiens.
- **En ligne :** formulaires de retard/perte et d'incident de livraison, ainsi que chatbot et agents en direct sur [auspost.com.au/help](https://auspost.com.au/help).
- **Sur place en Australie :** plus de 4 100 bureaux de poste peuvent enregistrer une réclamation.

StarTrack, la branche messagerie express et fret d'Australia Post, dispose de sa propre ligne (`13 23 45`) et de ses formulaires, mais ses envois se suivent sur le même réseau et dans l'application AusPost.

> Australia Post indique ne jamais appeler, envoyer de SMS ni d'e-mail pour demander des coordonnées personnelles ou bancaires non sollicitées ni un paiement inattendu (faux frais de relivraison, vérification de code postal, absence de lieu sûr), même lorsque le message s'affiche sous l'intitulé *AusPost*. Transfère les messages suspects à `scams@auspost.com.au` et consulte [SMS d'arnaque à la livraison](guide:delivery-scam-texts).

## Suivre un colis Australia Post avec Peek

[Peek](/) identifie immédiatement Australia Post pour les identifiants d'article de 21 et 23 caractères ainsi que pour les numéros internationaux EMS, colis ou lettres suivies terminés par `AU` (`C…AU`, `E…AU`, `L…AU`) dotés d'un chiffre de contrôle valide, et affiche « Transporteur détecté » avec « Changer ». Les numéros d'expédition (y compris StarTrack), les codes-barres prépayés à 22 chiffres et les codes `R` à 25 caractères ne suffisent pas à eux seuls : pendant l'ajout, Peek interroge les transporteurs plausibles (« Vérification des services de suivi… ») et affiche « Australia Post a ce colis. » dès que le suivi répond, sinon te laisse choisir Australia Post sous « Correspond à ce numéro » avec « Changer ». Les autres numéros postaux terminés par `AU` s'ouvrent comme envoi postal pendant que Peek interroge Australia Post. Les liens `auspost.com.au` contenant `id=` (y compris `#/search?id=`) ou `/mypost/track/details/` fonctionnent et désignent Australia Post quel que soit le numéro.

Si la passerelle d'Australia Post refuse la requête directe de Peek, un navigateur sur le serveur de Peek ouvre `auspost.com.au` pour lire le suivi ; les services de suivi tiers ne détenant qu'une infime partie des étapes d'Australia Post, l'historique peut rester limité si ce navigateur est lui aussi bloqué.

Peek affiche le statut, chaque scan avec sa localité sur la carte, le nom de l'offre (`Parcel Post Large Satchel`, etc.), l'heure exacte de livraison, le pays de destination et le nom du bureau de poste ou du *Parcel Locker* où attend le colis, tout en écartant le nom du destinataire, l'adresse complète et les photos de livraison (sur un lien partagé, une livraison affiche uniquement `Delivered`). Les scans propres à Australia Post portent leur fuseau australien et ceux relayés depuis un opérateur postal étranger l'horloge du pays concerné, tous convertis dans le fuseau de ton appareil — sur `peektracker.com` et dans les applications iPhone et Android, le bas de l'historique précise « Heures dans ton fuseau horaire : … ». Tant que l'envoi est en cours, Peek affiche la date ou la fourchette de livraison annoncée par Australia Post — et, le jour même, le créneau horaire tant qu'il n'est pas dépassé —, puis masque cette estimation dès que le colis est livré, retourné ou prêt à être retiré. Lorsqu'Australia Post ne fournit aucune date (envoi déposé en boîte de rue ou adresse manuscrite), son propre outil web peut néanmoins en estimer une à partir du code postal. Peek ne peut ni rediriger un colis ni modifier une consigne.

Tant que le numéro n'est pas encore connu, Peek affiche « En attente de Australia Post » ; après deux jours sans reconnaissance par Australia Post ni par une autre source, il affiche « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. », et après quatre jours sans nouveau scan en cours d'acheminement, il propose « Demande de l’aide à Australia Post ».

Tant que tu gardes le colis ouvert à l'écran, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes (toutes les 5 minutes lorsqu'il doit passer par le navigateur) le jour où Australia Post scanne le colis en livraison, à partir de 6 h heure locale ou une demi-heure avant l'ouverture du créneau annoncé, tant que le suivi d'Australia Post répond (via des services de suivi seuls : toutes les heures, toutes les 3 heures après une journée calme et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou si les notifications sont activées ; lorsqu'un opérateur étranger assure la livraison finale, Peek suit la cadence de cet opérateur) — ; le reste du temps, ainsi qu'après deux jours sans nouvelle étape (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), le contrôle est horaire. Tu peux choisir entre « Chaque scan », « Étapes importantes » et « Livraison seulement ». Un envoi expédié depuis l'étranger vers l'Australie avec un numéro postal international bascule vers Australia Post dès que celle-ci publie une progression.

## Questions fréquentes sur le suivi Australia Post

### Que signifie Pending sur le suivi Australia Post ?

Le colis n'a pas encore été scanné dans le système d'Australia Post : il n'a peut-être pas encore été remis ou a été déposé dans une boîte aux lettres de rue en Australie. Une mise à jour apparaît normalement sous 24 à 48 heures. Un ancien numéro sans activité depuis plus de 3 mois affiche également `Pending`.

### Combien de temps Australia Post garde-t-elle un colis en Parcel Locker ?

**2 jours ouvrés** (48 heures hors week-ends et jours fériés). Ensuite, le colis est transféré au bureau de poste voisin proposant le retrait, qui le conserve **10 jours ouvrés** avant de le renvoyer à l'expéditeur.

### Tous les envois Australia Post ont-ils un suivi ?

Non : les tarifs *International Post Registered* et *International Economy* (Air et Sea) n'incluent pas le suivi en standard. Pour un envoi entrant vers l'Australie, la présence d'un suivi dépend du service choisi auprès de la poste ou du transporteur du pays d'expédition.

:::sources
- [Australia Post : Suivi de colis](https://auspost.com.au/mypost/track) – saisie jusqu'à 10 numéros de suivi à la fois
- [Australia Post : Lost tracking number](https://auspost.com.au/help/parcel-tracking/tracking-support/lost-tracking-number) – où trouver le code, pas de recherche par nom et nouvel essai après 24 h
- [Australia Post : Help with tracking](https://auspost.com.au/help/parcel-tracking/tracking-support/help-with-tracking) – identifiant d'article (article ID), centres de tri et événements récents
- [Australia Post : What your status means](https://auspost.com.au/help/parcel-tracking/tracking-support/what-your-status-means) – Pending (24–48 h et plus de 3 mois), tentative de livraison, carte Sorry We Missed You, It's on its way depuis l'étranger et SMS de créneau de 2 h
- [Australia Post : Tracking service](https://auspost.com.au/personal/receiving/tracking-service) – suivi sans compte et centres de tri de Redbank, Chullora et Sunshine West
- [Australia Post : Taking longer than expected](https://auspost.com.au/help/parcel-tracking/taking-longer-than-expected) – Pending et Ready for processing au-delà d'une semaine, délai de 5 jours ouvrés
- [Australia Post : Delays and disruptions](https://auspost.com.au/help/parcel-tracking/delays-and-disruptions) – signification de Possible delay
- [Australia Post : Return to sender](https://auspost.com.au/help/delivered-parcel/return-to-sender) – Returning to sender, Returned et Contact sender après 10 jours ouvrés
- [Australia Post : Missed parcel deliveries](https://auspost.com.au/personal/receiving/parcel-deliveries/missed-parcel-deliveries) – dépôt en bureau de poste ou consigne, pièces d'identité, procuration et transfert à 7,55 AU$
- [Australia Post : Requested delivery but parcel at Post Office](https://auspost.com.au/help/delivered-parcel/parcel-at-post-office) – garde de 10 jours ouvrés, pas de relivraison depuis un bureau de poste et formulaire d'incident
- [Australia Post : Transfer to another post office](https://helpandsupport.auspost.com.au/s/products-and-services?transfertoPO) – formulaire de transfert mentionnant encore 7,20 AU$
- [Australia Post : 24/7 Parcel Lockers](https://auspost.com.au/help/collection-delivery/parcel-lockers) – ouverture 24/7 sur la plupart des sites, garde de 48 h hors week-ends et fériés, code d'accès et rappel 24 h avant transfert
- [Australia Post : Redirect parcel or change address](https://auspost.com.au/help/parcel-tracking/redirect-parcel-change-address) – redirection via MyPost avant la tournée et jour ouvré supplémentaire
- [Australia Post : Leave in a safe place (Safe Drop)](https://auspost.com.au/help/collection-delivery/safe-drop) – activation jusqu'à 1 h avant et tournées de 7 h à 17 h du lundi au vendredi
- [Australia Post : AusPost app](https://auspost.com.au/personal/receiving/australia-post-app) – notifications numériques, avis papier et créneau de 2 heures
- [Australia Post : No recent update](https://auspost.com.au/help/parcel-tracking/no-recent-update) – absence temporaire de scans, 5 jours ouvrés, réclamation après 10 jours ouvrés et horaires téléphoniques
- [Australia Post : Lost or late items](https://auspost.com.au/personal/receiving/parcel-deliveries/lost-late-items) – formulaire d'enquête en ligne et compte MyPost
- [Australia Post : Customs](https://auspost.com.au/help/parcel-tracking/international-parcel/customs) – jusqu'à 15 jours en douane australienne, courrier des autorités et réclamation auprès de la poste d'origine
- [Australia Post : International tracking](https://auspost.com.au/help/parcel-tracking/international-parcel/tracking) – tarifs sans suivi inclus (International Post Registered, International Economy) et suivi entrant
- [Australia Post : Delivered status but parcel not received](https://auspost.com.au/help/delivered-parcel/parcel-not-received) – vérifications, photo de livraison et signalement à la police
- [Australia Post : Damaged parcel](https://auspost.com.au/help/delivered-parcel/damaged-parcel) – constat d'avarie en bureau de poste
- [Australia Post : Compensation](https://auspost.com.au/personal/receiving/parcel-deliveries/compensation) – plafond de 100 AU$, bénéficiaire, délai de 6 mois et recours préalable au vendeur
- [Australia Post : Complaints and feedback](https://auspost.com.au/about-us/corporate-information/complaints-and-feedback) – numéros 13 76 78, +61 3 8340 7239, StarTrack 13 23 45 et aide en bureau de poste
- [Australia Post : Help & support](https://auspost.com.au/help) – formulaires en ligne et chat
- [Australia Post : Expected delivery date](https://auspost.com.au/help/parcel-tracking/tracking-support/expected-delivery-date) – date ou plage prévue, cas sans date et estimation par code postal
- [Australia Post : StarTrack tracking](https://auspost.com.au/help/startrack/tracking) – suivi des envois StarTrack sur le même outil et dans l'application AusPost
- [Australia Post : Scam alerts](https://auspost.com.au/about-us/about-our-site/online-security-scams-fraud/scam-alerts) – faux messages AusPost et signalement à scams@auspost.com.au
- [Australia Post : About us](https://auspost.com.au/about-us) – réseau de plus de 4 100 bureaux de poste en Australie
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – formats d'identifiants et liens reconnus, passerelle et lecture navigateur, créneau et date prévue, horloges locales et confidentialité
:::
