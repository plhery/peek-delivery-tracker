---
title: Suivi YWE (YanwenExpress) : numéro, scans et aide
description: Suis un colis YWE (YanwenExpress) aux États-Unis : format du numéro YWE à 17 caractères, sens de chaque scan du hub jusqu’à la porte et qui contacter.
slug: suivi-ywe
published: 2026-11-18
updated: 2026-11-18
---

YWE (YanwenExpress, exploité par Yanwen Express LLC) est un transporteur du dernier kilomètre aux États-Unis : il achemine les colis depuis ses plateformes américaines jusqu’à la porte du destinataire pour des boutiques en ligne, des enseignes et des places de marché. Ses numéros se composent des lettres YWE suivies de 14 chiffres : colle le tien dans le champ ci-dessus, ou sur le suivi propre de YWE sur [yanwenexpress.com](https://www.yanwenexpress.com/tracking.html).

## Numéro de suivi YWE : à quoi il ressemble

Un numéro YWE compte 17 caractères au total : le préfixe YWE suivi de 14 chiffres. Exemples avec des chiffres inventés :

| Numéro | Ce que c’est |
| --- | --- |
| `YWE48291057364918` | YWE et 14 chiffres : un numéro de suivi YWE. |
| `YWE4829105736491` | Seulement 13 chiffres : ce n’est pas le format de YWE. Vérifie s’il manque un chiffre ; si aucun transporteur n’en répond, sa page sur peektracker.com affiche « Ça commence comme un numéro de suivi YWE, mais il manque un caractère : il y a peut-être une faute de frappe. Compare-le avec ton message d’expédition. » |
| `YWLAX482910573649` | YW, trois lettres et 12 chiffres : un numéro [Yanwen](carrier:yanwen), pas un numéro YWE. |

Les espaces et les minuscules n’ont aucune importance quand tu colles le numéro dans Peek.

**Où le trouver.** Dans l’e-mail d’expédition de la boutique ou sur la page de ta commande. Si tu ne le retrouves pas, consulte [comment trouver son numéro de suivi](guide:find-tracking-number).

**Un lien de suivi.** Un lien vers le suivi de YWE qui se termine par `?label=` suivi d’un numéro YWE à 14 chiffres ouvre directement ce colis sous YWE quand tu colles le lien entier dans Peek. Le suivi de YWE accepte jusqu’à 30 numéros à la fois (si tu en saisis davantage, son site ne garde que les 30 premiers).

## Statuts de suivi YWE expliqués

Le suivi de YWE affiche chaque scan avec sa date et son heure, le décalage par rapport à GMT ainsi que la ville et l’État américain, du plus récent au plus ancien. Les messages sont en anglais. Dans l’ordre habituel du parcours aux États-Unis :

| Message de YWE | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Shipping Order Created.` | L’expéditeur a enregistré la livraison auprès de YWE, mais YWE n’a pas encore le colis en main. | « Étiquette créée » |
| `Container picked up by YWE,LAX01 Awaiting Item.` | YWE a récupéré aux États-Unis le conteneur aérien dans lequel le colis a voyagé, avant le premier scan individuel du colis. | « En transit » |
| `Container received` | La plateforme de YWE a réceptionné le conteneur. | « En transit » |
| `Parcel received by in LAX01.` | Premier scan du colis lui-même dans la plateforme américaine de YWE. `Parcel received by NJC01.`, sans « in », désigne la même étape dans un autre hub. | « En transit » |
| `Parcel departed from Facility.` | Le colis a quitté la plateforme. | « En transit » |
| `Parcel received by transit facility.`, `In processing at transit facility.`, `Parcel departed from Transit Facility.`, `In transit - departure`, `In transit to next facility.` | Passage par un centre de transit intermédiaire aux États-Unis, avant l’agence qui dessert le secteur de livraison. | « En transit » |
| `Parcel received by local  Facility.` | Arrivée à l’agence locale qui assure la tournée dans le secteur du destinataire aux États-Unis. | « En transit » |
| `Parcel is out for delivery.` | Le colis est en tournée de livraison aux États-Unis. | « En livraison » |
| `Parcel has been delivered.(Front door)` | Colis livré aux États-Unis. La mention entre parenthèses indique l’endroit où le livreur l’a déposé. | « Livré » |
| `Parcel delivery abnormal [Restricted Address].Parcel will be returned to the warehouse.` | Échec de livraison aux États-Unis pour le motif indiqué entre crochets : le colis retourne à l’entrepôt américain de YWE, pas chez l’expéditeur. | « Tentative de livraison » |
| `Parcel delivery failed [Wrong Address]. Parcel will be returned to the warehouse.` | Même retour vers l’entrepôt américain de YWE, formulé comme échec (par exemple adresse erronée ou refus), d’où une nouvelle mise en livraison reste possible. | « Tentative de livraison » |
| `Return to sender` | Le retour vers l’expéditeur a commencé ; le colis ne lui est pas encore revenu. | « Problème signalé » |
| `Returned to sender, shipment has been received in sender warehouse.` | Le colis est revenu dans l’entrepôt de l’expéditeur. | « Retourné à l’expéditeur » |

**Codes, motifs et lieux de dépôt.** `LAX01` et `NJC01` sont des codes de plateformes américaines de YWE : sur d’autres hubs, leur propre code prend cette place. De même, `[Restricted Address]` ou `[Wrong Address]` ne sont que des exemples de motifs entre crochets, et `(Front door)` un exemple d’emplacement de dépôt entre parenthèses.

**L’onglet Alert ne signale pas toujours une anomalie.** Le suivi de YWE classe les colis dans cinq onglets selon leur dernier scan : `Awaiting`, `Transit`, `Delivered`, `Alert` et `Returned`. Il range `Parcel received by local  Facility.` (avec deux espaces entre `local` et `Facility.`), c’est-à-dire la réception à l’agence locale de livraison, sous `Alert` alors qu’il s’agit d’une étape normale. Peek l’affiche comme « En transit ».

**Autres formulations.** Un scan qui ne figure pas dans ce tableau apparaît dans Peek tel que YWE l’a écrit, et Peek en déduit l’étape à partir de ses mots quand c’est possible.

**Heures.** Chaque scan est fourni par YWE avec le décalage horaire par rapport à GMT du hub concerné. Peek lit l’instant exact de cette façon et convertit toutes les heures dans le fuseau horaire de ton appareil ; sur peektracker.com et dans l’application iPhone, l’historique se termine par « Heures dans ton fuseau horaire : » suivi du nom de ce fuseau.

## Qui livre les colis YWE

**États-Unis.** Aux États-Unis, YWE assure lui-même le dernier kilomètre en s’appuyant sur ce que son site appelle des « Delivery Service Partner networks nationwide » : des équipes locales de livraison partenaires qui rejoignent son réseau à travers les États-Unis pour livrer ou collecter des colis pour son compte. Dans le code de Peek, un historique YWE va de la plateforme américaine de YWE jusqu’à la porte du destinataire et ne nomme aucun autre transporteur.

Sur sa page de présentation, YWE indique avoir démarré à Los Angeles en 2024 et prévoir de couvrir la majeure partie des États-Unis d’ici fin 2026.

**Tentatives de livraison et retrait aux États-Unis.** YWE ne publie aucune règle sur le nombre de passages, les avis de passage ni d’éventuels points de retrait aux États-Unis. Après un échec de livraison aux États-Unis, ses scans précisent que le colis retourne à l’entrepôt de YWE (`Parcel will be returned to the warehouse.`). Pour demander une nouvelle présentation ou transmettre des consignes d’accès, écris à YWE par e-mail (voir ci-dessous) avec le numéro de suivi, l’adresse complète aux États-Unis et tes instructions de livraison. Voir aussi [livraison manquée](guide:missed-delivery).

**France, Belgique, Suisse, Canada et reste du monde.** YWE ne mentionne aucun service en dehors des États-Unis. Si un vendeur t’a communiqué un numéro YWE pour une livraison en France, en Belgique, en Suisse, au Canada ou dans tout autre pays hors des États-Unis, demande au vendeur quel transporteur assure réellement la livraison sur place.

**YWE et Yanwen.** [Yanwen](carrier:yanwen) est un transporteur distinct dans Peek, avec ses propres formats de numéros et son propre suivi, et le site de YWE ne précise pas si les deux sociétés sont liées. Lorsque le suivi de Yanwen désigne `YanwenExpress` comme distributeur d’un colis vers les États-Unis et fournit un numéro YWE, Peek affiche « Livraison par YWE » et interroge aussi le suivi de YWE. Tant que YWE n’a que l’annonce du colis sans scan propre, Peek affiche « Annoncé à YWE, pas encore reçu ». Dès que YWE publie un scan au moins aussi récent que le dernier scan de Yanwen, Peek suit le colis sur le suivi de YWE tout en conservant les scans précédents de Yanwen.

## Colis YWE non reçu ou suivi bloqué

**`NOT_FOUND`.** Le suivi de YWE affiche ce code lorsqu’il n’a aucun scan pour le numéro saisi, et énumère les causes possibles : le colis n’a pas encore été pris en charge ou scanné, le numéro comporte une erreur, ou l’envoi est si ancien que son historique n’est plus disponible. Sur peektracker.com, Peek affiche « En attente de YWE » et continue de vérifier. Compare le numéro avec le message du vendeur : YWE suivi de 14 chiffres. Si rien n’apparaît après quelques jours, demande au vendeur si le colis a bien été expédié.

**Bloqué sur `Shipping Order Created.`** YWE a reçu les données électroniques de l’envoi, mais pas encore le colis. Comme YWE effectue son premier scan dans sa plateforme aux États-Unis, demande au vendeur où se trouve actuellement le colis et quand il est parti. S’il a été expédié depuis la Chine vers les États-Unis, consulte [suivre un colis venu de Chine](guide:tracking-from-china).

**Bloqué sur `Container picked up by YWE,LAX01 Awaiting Item.`** YWE a pris en charge le conteneur aérien aux États-Unis, mais n’a pas encore scanné ton colis individuellement à l’ouverture du conteneur.

**`Parcel delivery abnormal` ou `Parcel delivery failed`.** La tentative de livraison aux États-Unis a échoué pour la raison indiquée entre crochets, et le colis repart vers l’entrepôt américain de YWE. Envoie un e-mail à YWE avec le numéro de suivi, l’adresse complète aux États-Unis et d’éventuelles instructions d’accès, et préviens également le vendeur.

**`Return to sender`.** Le colis repart vers l’expéditeur. Dès que la ligne `Returned to sender, shipment has been received in sender warehouse.` apparaît, l’entrepôt de l’expéditeur l’a reçu : demande alors au vendeur un remboursement ou un nouvel envoi. Voir [colis retourné à l’expéditeur](guide:returned-to-sender).

**Marqué livré aux États-Unis, mais rien reçu.** La mention entre parenthèses dans le scan de livraison, comme `(Front door)`, indique où le colis a été laissé. Sur un colis livré aux États-Unis, le suivi de YWE propose un lien `view receipt` : saisis le code ZIP américain du destinataire dans le champ `Recipient's postal code` pour consulter les photos de livraison lorsqu’il y en a. Écris ensuite à YWE et signale le problème au vendeur. Voir [colis indiqué livré mais non reçu](guide:delivered-not-received).

**Colis perdu et réclamations.** Selon la politique de confidentialité du portail client de YWE, l’ouverture de compte est réservée aux entreprises clientes (`corporate/business clients`) : c’est donc la boutique ou la plateforme qui a réservé la livraison aux États-Unis qui est cliente de YWE, pas le destinataire. Le site de YWE ne publie aucune procédure d’indemnisation destinée aux particuliers. Pour un remboursement ou un renvoi, adresse-toi au vendeur ou à la place de marché. Voir [colis perdu](guide:lost-parcel) et [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

## Contact YWE et service client

Le site de YWE, uniquement en anglais, indique :

- **E-mail** : un lien de messagerie figure dans le pied de page de chaque page de `yanwenexpress.com`. En cliquant dessus, une fenêtre précise les éléments à fournir : ta question, le numéro de suivi du colis, puis dans le corps du message l’adresse complète du destinataire, d’éventuelles instructions de livraison, ses coordonnées (numéro de téléphone et adresse e-mail) et l’objet précis de la demande.
- **Téléphone** : aucun numéro affiché. La page d’assistance (`Customer support`) mentionne « Calling us for further enquiries », mais ne publie ni numéro de téléphone ni horaires d’ouverture.
- **FAQ** : sur la page `Customer support`, les questions-réponses s’adressent aux partenaires de livraison (`Delivery Service Partner`) et non aux destinataires qui attendent un colis.

Pour toute question sur ta commande ou un remboursement, contacte le vendeur. Pour la livraison aux États-Unis elle-même, écris à YWE via le lien e-mail du pied de page.

## Suivre un colis YWE avec Peek

[Peek](/) reconnaît immédiatement un numéro YWE à sa forme — YWE suivi de 14 chiffres — qu’aucun autre transporteur de son catalogue n’utilise, et interroge directement le suivi de YWE. Coller un lien `yanwenexpress.com/tracking.html?label=` contenant un numéro YWE fonctionne aussi. Aucun compte, code postal ni numéro de téléphone n’est requis dans Peek.

Peek affiche le statut actuel, chaque scan avec sa ville, son État et son pays, ainsi que l’heure de livraison. Les scans en doublon sont écartés, tout comme le nom de la personne ayant réceptionné le colis lorsqu’un scan de livraison en mentionne un.

Sur son propre site, le suivi de YWE peut aussi montrer un créneau de livraison de deux heures pendant le transit aux États-Unis, une carte avec la position du chauffeur et les photos de preuve de livraison accessibles par code ZIP. Peek ne reprend ni ces éléments ni les lignes de rue d’un scan, et n’affiche pas de date de livraison estimée.

Comme le suivi de YWE ne nomme aucun autre transporteur, un colis ajouté directement par son numéro YWE n’affiche pas de ligne « Livraison par » dans Peek, qui reste sur le suivi de YWE jusqu’à la livraison. Si le suivi de YWE est temporairement injoignable, Peek interroge des services de suivi en attendant.

Tant que tu gardes le colis ouvert, ou le jour de sa livraison avec les notifications activées, Peek vérifie jusqu’à toutes les 10 minutes — et jusqu’à toutes les 2 minutes à partir de 6 h du matin, heure locale, le jour où le colis passe en livraison — tant que le suivi propre de YWE répond (via les services de suivi seuls : toutes les heures, toutes les 3 heures après une journée sans nouveau scan, et jusqu’à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont activées) ; le reste du temps, ainsi qu’après deux jours sans nouvelle mise à jour (12 heures tant que le colis indique encore « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Tu peux choisir d’être prévenu à « Chaque scan », aux « Étapes importantes » ou à la « Livraison seulement ». Peek ne peut contacter ni YWE ni le vendeur à ta place.

## Questions fréquentes sur le suivi YWE

### Qu’est-ce que YWE (YanwenExpress) ?

Un transporteur du dernier kilomètre aux États-Unis, exploité par Yanwen Express LLC. Il livre les colis de boutiques en ligne, d’enseignes et de places de marché via un réseau d’équipes locales partenaires aux États-Unis, et indique avoir débuté à Los Angeles en 2024.

### YWE est-il le même transporteur que Yanwen ?

Peek les traite comme deux transporteurs distincts : Yanwen possède ses propres formats de numéros et son propre suivi, et le site de YWE ne dit pas si les deux entreprises sont liées. Quand le suivi de Yanwen désigne `YanwenExpress` comme distributeur d’un colis aux États-Unis avec un numéro YWE, Peek prend le relais sur le suivi de YWE.

### Où suivre un colis YWE ?

Dans le champ en haut de cette page, ou sur le suivi de YWE sur `yanwenexpress.com`, qui accepte jusqu’à 30 numéros à la fois. Entre le numéro complet : les lettres YWE suivies des 14 chiffres.

### Que signifie « Container picked up by YWE » ?

YWE a récupéré aux États-Unis le conteneur aérien dans lequel ton colis est arrivé. Le code placé après la virgule, comme `LAX01`, identifie la plateforme américaine de YWE. Le premier scan individuel de ton colis, `Parcel received by in LAX01.`, intervient ensuite. Peek affiche « En transit » pour ces deux étapes.

### Qui contacter pour un colis YWE manquant ?

D’abord le vendeur ou la plateforme où tu as commandé, car c’est l’entreprise expéditrice qui est cliente de YWE. Pour une question pratique sur la livraison aux États-Unis, écris à YWE via l’adresse e-mail figurant dans le pied de page de son site, en joignant le numéro de suivi ; YWE ne publie aucun numéro de téléphone.

:::sources
- [YanwenExpress : page d’accueil](https://www.yanwenexpress.com/) – livraison du dernier kilomètre aux États-Unis via des réseaux de Delivery Service Partners, limite de 30 numéros à la fois, lien e-mail du pied de page et informations demandées
- [YanwenExpress : About us](https://www.yanwenexpress.com/about.html) – lancement à Los Angeles en 2024, couverture prévue de la majeure partie des États-Unis d’ici fin 2026
- [YanwenExpress : Tracking](https://www.yanwenexpress.com/tracking.html) – scans avec décalage GMT, ville et État, onglets Awaiting, Transit, Delivered, Alert et Returned, code NOT_FOUND et causes possibles, créneau de livraison, carte du chauffeur, lien « view receipt » avec le code postal du destinataire, 30 numéros à la fois
- [YanwenExpress : Customer support](https://www.yanwenexpress.com/contactus.html) – mention d’appel sans numéro ni horaires affichés, FAQ destinées aux Delivery Service Partners
- [YanwenExpress : Join us](https://www.yanwenexpress.com/joinus.html) – équipes locales à travers les États-Unis rejoignant son réseau de livraison et de collecte
- [YanwenExpress : Privacy Policy](https://customer-portal.yanwenexpress.com/agreement/Yanwen_privacy_policy) – Yanwen Express LLC, ouverture de compte réservée aux entreprises clientes
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – noms et alias, format YWE à 14 chiffres, suivi et lien `?label=`, libellés de scans et étapes normalisées, codes de hubs, classement sous Alert, décalages GMT, champs conservés ou écartés par Peek et relais depuis Yanwen
:::
