---
title: Suivi DAI Post : numéros, statuts et aide à la livraison
description: Suivre un colis DAI Post (Parcel Right) en Australie, Nouvelle-Zélande ou à Singapour : formats des numéros, statuts, dépôt sans signature et contacts.
slug: suivi-dai-post
published: 2026-11-23
updated: 2026-11-23
---

DAI Post est une société australienne de livraison pour boutiques en ligne, fondée à Melbourne en 2000. Elle achemine des colis depuis les États-Unis, le Royaume-Uni, l’Europe et l’Asie vers les acheteurs en Australie, en Nouvelle-Zélande et à Singapour. Son propre service de livraison du dernier kilomètre s’appelle Parcel Right. Suis un colis sur la [page de suivi de DAI Post](https://daipost.com/track), ou colle le numéro dans la case ci-dessus. Le numéro figure dans la confirmation de commande de la boutique ; si tu ne le trouves pas, demande à la boutique.

## Numéro de suivi DAI Post : à quoi il ressemble

DAI Post suit les numéros que les boutiques impriment sur leurs colis, qui prennent donc plusieurs formes. Son suivi accepte jusqu’à 10 numéros à la fois. Les formes connues par Peek, avec des chiffres inventés :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `NZC482910375` | `NZC` suivi de neuf chiffres : dans le code de Peek, le numéro DAI Post pour les colis à destination de la Nouvelle-Zélande. Peek nomme DAI Post aussitôt. |
| `00393580944829103754` | 20 chiffres commençant par `0039358094`, le dernier étant une clé de contrôle : un code SSCC imprimé sur l’étiquette, parfois sous la forme `(00) 3 9358094 482910375 4`. D’autres transporteurs utilisent aussi 20 chiffres, donc Peek ne peut pas être certain à partir de la seule forme. |
| `KPR48291037561` | Une référence propre à la boutique, lettres puis chiffres. Peek ne la reconnaît pas à sa forme. |

Espaces, parenthèses et minuscules n’ont pas d’importance. Si un chiffre d’un numéro à 20 chiffres est faux, sa clé de contrôle échoue et Peek ne l’associe pas à DAI Post : vérifie le numéro. Plus d’informations dans [comment trouver un numéro de suivi](guide:find-tracking-number) et [formats de numéros de suivi](guide:tracking-number-formats).

## Statuts de suivi DAI Post expliqués

Le suivi de DAI Post affiche une phrase conviviale pour chaque étape. Certains colis affichent une version plus courte de la même étape, comme `Checked in at origin hub`. Peek lit le code de l’étape, donc les deux formulations signifient la même chose. Les lignes connues de Peek, dans l’ordre :

| Ce que dit DAI Post | Ce que cela signifie | Ce que montre Peek |
| --- | --- | --- |
| `We’ve received your delivery details. We’ll let you know as soon as your parcel is in our hands.` ou `Customer label information received` | La boutique a envoyé tes coordonnées. DAI Post n’a pas encore le colis. | « Étiquette créée » |
| `Your parcel’s checked in and being prepared for its journey.` ou `Checked in at origin hub` | Le hub d’origine de DAI Post a pris en charge le colis. | « Pris en charge » |
| `We’ve processed your parcel at our origin facility. Next, on the move to you!` ou `Parcel processed at origin facility` | Trié et prêt à partir. | « En transit » |
| `There’s a departure delay. Thanks for your patience, we’ll update you soon.` ou `There’s a transit delay. Thanks for your patience, we’ll update you soon.` | Le vol part en retard, ou il y a un retard en cours de route. | « En transit » |
| `Your parcel is in the air and is heading your way.` ou `Parcel departed origin country` | En vol au départ du pays d’origine. | « En transit » |
| `Parcel arrived in trans-shipment country.`, puis `Your parcel’s left the transfer country and is back on the move.` | Escale dans un pays de transit. | « En transit » |
| `Touchdown! Your parcel has landed.` ou `Parcel arrived in destination country` | Arrivé dans ton pays. | « En transit » |
| `We’ve collected your parcel from the airline and it’s being processed.` ou `Collected from the airline for processing` | DAI Post l’a récupéré auprès de la compagnie aérienne. | « En transit » |
| `Your parcel is being checked by customs. We’ll let you know when it’s cleared.` ou `Parcel processed by clearance facility` | La douane contrôle le colis. | « À la douane » |
| `Customs clearance delay` | La douane retient encore le colis. | « À la douane » |
| `All clear! Your parcel’s passed customs and is ready for the next leg.` ou `Customs cleared` | La douane l’a libéré. | « En transit » |
| `We’ve got your parcel and it’s been sorted for delivery.` ou `Parcel received and sorted` | Trié pour la livraison. | « En transit » |
| `Your parcel’s on an interstate journey. We’ll keep you updated as it travels.` | En trajet entre deux États. | « En transit » |
| `Your parcel’s arrived at our local facility, it's on the way.` | Arrivé dans un dépôt proche de chez toi. | « En transit » |
| `Your parcel’s on its way to you, almost there!` | En route vers le dépôt de livraison, pas encore en tournée. | « En transit » |
| `It’s happening, your parcel will be delivered today. Get ready!` ou `Parcel out for final mile carrier delivery` | En livraison aujourd’hui. | « En livraison » |
| `Parcel unsafe to leave at address` | Le chauffeur n’a pas pu le laisser sur place (voir ci-dessous). | « Tentative de livraison » |
| `Parcel available for collection` | T’attend dans un point de retrait. | « Prêt à être retiré » |
| `Delivered! Your parcel has arrived. Enjoy!` | Livré. | « Livré » |
| `Delivered, left where you can find it.` ou `Parcel delivered - authority to leave` | Déposé à ton adresse sans signature. | « Livré » |

Pour un numéro inconnu, le suivi de DAI Post répond `Sorry, we couldn't find your parcel.` Plus dans [signification des statuts de suivi](guide:tracking-statuses).

**Heures.** DAI Post indique chaque heure dans l’heure locale du lieu du scan, sans fuseau horaire. Peek déduit le fuseau à partir du lieu et convertit les heures dans le fuseau horaire de ton appareil ; l’heure de la première ligne peut parfois être décalée.

## Livraison DAI Post : dépôt sans signature, retrait et retours

DAI Post affirme que son réseau dessert 100 % de la population australienne. L’heure de passage dépend de la tournée du chauffeur, qui varie selon les colis du jour et le trafic.

- **Dépôt sans signature (*Authority to leave*).** Aucune signature n’est requise. Si personne ne répond et que le chauffeur juge l’endroit sûr, il laisse le colis.
- **Endroit jugé peu sûr.** Si le colis serait trop exposé, il est déposé au point de retrait le plus proche de ton secteur et DAI Post t’envoie les instructions de retrait. Sur le site de DAI Post, la ligne `Parcel available for collection` renvoie vers une carte du point ; Peek n’affiche que la ville.
- **7 jours pour le retirer.** Un colis non retiré sous 7 jours repart au dépôt de DAI Post, puis chez la boutique. Il en va de même si les coordonnées de livraison sont incomplètes.
- **Messages.** Tu ne reçois de notifications aux étapes clés que si la boutique a transmis ton adresse e-mail et ton numéro de mobile à DAI Post.

Les conditions de DAI Post précisent qu’en ton absence le colis peut être laissé dans un lieu sûr ou déposé en *Collection Point*, et qu’il est alors considéré comme livré. Ses pages ne disent pas qui assure la remise finale en Nouvelle-Zélande ou à Singapour. Voir [livraison manquée](guide:missed-delivery) et [colis non retirés](guide:uncollected-parcel).

## Douane sur un colis DAI Post

**Retenu en douane (*Customs held*).** La page d’aide de DAI Post explique qu’un événement *Customs held* signifie que l’ABF ou le DAFF, les services australiens des frontières et de biosécurité, demandent probablement des informations complémentaires avant de libérer le colis. Demande au service client de DAI Post ce dont ils ont besoin : le formulaire de demande propose le motif « My parcel is Customs held. »

**Frais.** Les conditions de DAI Post rendent l’expéditeur responsable des frais d’envoi, taxes et droits compris. Si tu refuses de payer des frais à la livraison, l’expéditeur reste redevable et DAI Post peut retenir le colis jusqu’au paiement ou le renvoyer à l’expéditeur. Ce que tu dois éventuellement régler dépend des règles de ton pays : voir [colis bloqués en douane](guide:customs). Un message réclamant des frais semble suspect ? Voir [SMS d’arnaque à la livraison](guide:delivery-scam-texts).

## Suivi DAI Post bloqué, colis perdu ou retourné

**Aucune nouvelle ligne depuis des jours.** DAI Post ne scanne les colis qu’aux étapes clés et précise que le colis continue d’avancer entre deux scans. Sur les trajets entre États ou vers les régions, il peut sembler immobile plusieurs jours. Si le colis reste quatre jours sans nouvelle ligne sur « Étiquette créée », « Pris en charge » ou « En transit », Peek affiche « Aucune mise à jour depuis … » avec le jour de cette ligne et un lien « Demande de l’aide à DAI Post ». Voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Toujours sur « Étiquette créée ».** DAI Post n’a pas encore le colis. Si cela dure plusieurs jours, demande à la boutique s’il a bien été expédié.

**Livré, mais absent.** Avec le dépôt sans signature, cherche d’abord autour de chez toi. DAI Post peut t’envoyer par e-mail une preuve de dépôt sans signature sur demande via son formulaire, puis contacte la boutique. Voir [livré mais non reçu](guide:delivered-not-received).

**Perdu ou retourné.** Pour un colis jamais arrivé, utilise le formulaire de DAI Post avec le motif « I did not receive my parcel. » Dans tous les cas, c’est la boutique qui a commandé la livraison : demande-lui un remboursement ou un nouvel envoi. Voir [colis perdus](guide:lost-parcel) et [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contact DAI Post

Canaux de contact de DAI Post :

- **Téléphone :** Australie +61 3 8586 1200, Nouvelle-Zélande +64 800 451 718, Singapour +65 800 321 1587. Aucun horaire d’ouverture n’est précisé.
- **E-mail :** consumercare@daipost.com.
- **En ligne :** le [formulaire de demande](https://daipost.com/contact). Il demande ton nom, ton téléphone, ton e-mail, le numéro de suivi et le motif de ta demande.

## Suivre un colis DAI Post avec Peek

[Peek](/) suit un colis DAI Post avec le numéro seul, sans compte ni code postal.

- **Un numéro `NZC`.** Peek nomme DAI Post aussitôt (« Transporteur détecté »). Si DAI Post ne connaît toujours pas le numéro deux jours après sa première réponse, Peek te demande de le vérifier, de choisir un autre transporteur ou de supprimer le colis.
- **Un numéro à 20 chiffres commençant par `0039358094`.** Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Pendant la vérification, il affiche « Vérification des services de suivi… », et DAI Post figure parmi les premiers interrogés. Si DAI Post a le colis, Peek le nomme. Sinon, la carte affiche « Probablement DAI Post » et « En attente du premier scan de DAI Post » ; après une semaine, Peek suggère de vérifier le numéro auprès de l’expéditeur. Pour choisir DAI Post manuellement, appuie sur « Changer » : il figure sous « Correspond à ce numéro ».
- **Tout autre numéro.** Colle-le quand même, puis appuie sur « Changer » et choisis DAI Post (cherche DAI Post ou Parcel Right). Sur un colis déjà ajouté, utilise « Changer de transporteur ».
- **Un lien.** Colle un lien de suivi de daipost.com, parcelright.com.au ou parcelrig.ht : Peek extrait le numéro et nomme DAI Post immédiatement.

Peek affiche le statut, chaque ligne dans les mots de DAI Post, la ville et le pays de chaque scan, la date de livraison prévue lorsque DAI Post en fournit une, ainsi que l’heure de livraison. Il n’affiche pas de point de retrait, de nom de service, de poids ni de preuve de livraison. « Ouvrir le site DAI Post » ouvre ton numéro sur le suivi officiel. Dans le code de Peek, certains colis [Asendia](carrier:asendia), ainsi que certains colis [ePost Global](carrier:epost-global) vers la Nouvelle-Zélande, passent par DAI Post : leur suivi affiche ses scans.

Tant que le colis est ouvert dans Peek, ou le jour de sa livraison avec les notifications activées, Peek le vérifie jusqu’à toutes les 10 minutes, et toutes les 2 minutes à partir de 6 h heure locale le jour où DAI Post le scanne en livraison. Après deux jours sans nouvelle (12 heures tant qu’il reste sur « Étiquette créée » ou « Pris en charge »), Peek vérifie chaque heure, puis toutes les 6 heures après sept jours sans nouvelle sur « Étiquette créée ». Les notifications peuvent suivre « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut contacter ni DAI Post ni la boutique à ta place.

## Questions sur le suivi DAI Post

### Parcel Right est-elle la même entreprise que DAI Post ?

Parcel Right est la livraison du dernier kilomètre assurée par DAI Post. Dans Peek, tu peux chercher l’un ou l’autre nom.

### Que signifie « Parcel unsafe to leave at address » ?

Le chauffeur n’a trouvé personne et l’endroit n’était pas sûr. Le colis va au point de retrait le plus proche : retire-le sous 7 jours, sinon il retourne à la boutique.

### Combien de temps DAI Post met-elle pour livrer ?

Les conditions de DAI Post précisent que les délais standard sont des estimations et non des garanties. Son suivi peut afficher une date de livraison prévue, reprise par Peek.

:::sources
- [DAI Post : Home](https://daipost.com/) – Melbourne, 2000, colis depuis les États-Unis, le Royaume-Uni, l’Europe et l’Asie vers l’Australie, la Nouvelle-Zélande et Singapour, Parcel Right
- [DAI Post : Help and support](https://daipost.com/help-and-support) – téléphones, couverture, délais, absence de scans récents, notifications, dépôt sans signature, retrait, retours, douane, preuve de livraison
- [DAI Post : Delivery tracking](https://daipost.com/track) – suivi, jusqu’à 10 numéros
- [DAI Post : Contact](https://daipost.com/contact) – formulaire de demande et motifs, téléphones, consumercare@daipost.com
- [DAI Post : Terms & conditions](https://daipost.com/terms) – lieu sûr ou Collection Point, délais estimés, frais
- [Universal Parcel Scraper : DAI Post](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/dai-post) – noms, formats de numéros, liens, libellés et étapes, heures, livraison prévue
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection, scans DAI Post relayés par Asendia et ePost Global
:::
