---
title: Suivi DPD UK : numéros à 14 chiffres, statuts et contact
description: Suivez un colis DPD UK : format du numéro à 14 chiffres, sens des statuts en anglais, options Predict et Pickup au Royaume-Uni, douane UE et contact.
slug: suivi-dpd-uk
published: 2026-11-21
updated: 2026-11-21
---

DPD UK (`DPDgroup UK Ltd`) est la filiale britannique de livraison de colis du groupe Geopost, rattaché au groupe français [La Poste](carrier:la-poste). Au **Royaume-Uni**, elle livre les commandes de nombreuses boutiques en ligne et expédie aussi des colis vers l'Europe et le reste du monde. Vous pouvez suivre un envoi sur la page [Follow My Parcel de DPD UK](https://track.dpd.co.uk/) avec le numéro de référence et le code postal de livraison, ou coller directement le numéro de colis à 14 chiffres dans le champ ci-dessus. Ce numéro figure dans le message de la boutique, dans le SMS ou l'e-mail de DPD UK, ou près du code-barres sur l'avis de passage (*sorry we missed you*).

## Où trouver votre numéro de suivi DPD UK et à quoi ressemble-t-il ?

La boutique qui a expédié la commande vous communique le numéro de colis ; vous pouvez aussi ouvrir le lien reçu par SMS ou par e-mail de DPD UK. Au **Royaume-Uni**, si le chauffeur a laissé une carte *sorry we missed you*, le numéro se trouve à côté du code-barres, et la page `track.dpd.co.uk` accepte aussi le numéro de cet avis de passage (*Calling card number*) accompagné du code postal. Introuvable ? Consultez [comment trouver son numéro de suivi](guide:find-tracking-number).

Les pages d'aide de DPD UK ne publient pas de spécification officielle du format. Les numéros de colis observés sur le suivi de DPD UK comptent 14 chiffres, souvent précédés de `1550` ou `1597`, parfois suivis d'un quinzième caractère de contrôle (chiffre ou lettre) sur la page de suivi :

| Numéro (inventé) | Ce qu'il désigne |
| --- | --- |
| `15504829105710` | Numéro de colis DPD UK : 14 chiffres commençant par `1550` (ou `1597`). |
| `15504829105710S` | Le même numéro accompagné du caractère de contrôle DPD à la fin. |
| `18429105736482` | Autre séquence de 14 chiffres : format partagé entre DPD UK, d'autres filiales DPD et d'autres transporteurs. |

D'autres réseaux utilisent aussi 14 chiffres — notamment [DPD France](carrier:dpd-fr), [Chronopost](carrier:chronopost), [DPD Allemagne](carrier:dpd-de), [SEUR](carrier:seur), [BRT](carrier:brt) et DHL eCommerce UK — de sorte que les chiffres seuls ne suffisent pas toujours à identifier le transporteur. Au Royaume-Uni, les SMS légitimes s'affichent désormais sous le nom d'expéditeur `DPD UK` (ou `DPD Local` / `DPDgroup UK`), tandis que le nom du commerçant apparaît dans le corps du message. DPD Local est la marque sœur de DPD UK (l'application mobile DPD affiche les colis des deux marques). En **Irlande**, les livraisons relèvent de DPD Ireland, qui utilise ses propres numéros.

## Statuts de suivi DPD UK expliqués

Le suivi de DPD UK est uniquement en anglais. Voici les messages les plus fréquents dans l'ordre habituel du parcours :

| DPD UK affiche | Ce que cela signifie pour votre colis | Peek affiche |
| --- | --- | --- |
| `We've received your order details, but have not yet received your parcel` | Le vendeur a enregistré l'envoi, mais DPD UK n'a pas encore reçu le colis. | « Étiquette créée » |
| `We've received your order details, and we're expecting your parcel shortly` | L'étiquette est créée et DPD UK attend que l'expéditeur dépose le colis dans un relais DPD Pickup Shop. | « Étiquette créée » |
| `Your parcel has been dropped off at a DPD Pickup Shop` | L'expéditeur a remis le colis dans un point DPD Pickup Shop. | « Pris en charge » |
| `We have your parcel and it's on its way to our depot` | DPD UK a pris le colis en charge et l'achemine vers un dépôt. | « Pris en charge » (collecte directe) ou « En transit » (après dépôt en Pickup Shop) |
| `We have your parcel and it's on its way to you` | Le colis circule dans le réseau DPD UK vers votre région. | « En transit » |
| `Your parcel has arrived at our depot`, `Your parcel is at our depot` | Le colis est arrivé au dépôt DPD UK pour le tri. | « En transit » |
| `Your parcel is at our depot ready for delivery` | Le colis est au dépôt, prêt pour sa tournée ; la ligne redevient ensuite `Your parcel is at our depot`. | « En transit » |
| `A notification has been sent to advise you when the delivery will take place` | DPD UK a envoyé le SMS ou l'e-mail indiquant le jour ou le créneau de livraison. | L'étape précédente reste affichée |
| `As requested, your parcel will now be delivered on Thu 05 November 2026 at 11:00` | Vous avez choisi un autre jour de livraison (parfois avec une heure) ; la date et l'heure varient. | L'étape précédente reste affichée |
| `Your parcel will be with you today`, `Your parcel will be with you today between 14:18 and 15:18` | En tournée aujourd'hui, avec le créneau d'une heure lorsqu'il est communiqué (les horaires varient). | « En livraison » |
| `As requested, your parcel will now be left in your selected safe place` | Vous (ou vos préférences dans l'application DPD) avez désigné un lieu sûr où laisser le colis. | L'étape précédente reste affichée |
| `We missed you. Your driver won't be back today but we'll be back Wed 28 October with your parcel` | Le chauffeur n'a pas pu livrer et reviendra au jour indiqué (variable). Peek retire le prénom du chauffeur figurant dans la phrase de DPD UK. | « Tentative de livraison » |
| `Your parcel has been delivered` | Colis livré (le titre sur le site de DPD UK ajoute l'heure et la date). | « Livré » |
| `Your parcel is waiting for you at home (left in safe place)` | Livré à votre adresse dans le lieu sûr choisi. | « Livré » |
| `Your delivery has been cancelled` | L'expéditeur a annulé l'envoi ; DPD UK invite à contacter la boutique nommée. | « Problème signalé » |
| `We have your parcel and it's on its way back to the sender` | Retour en cours vers l'expéditeur (sauf avant le premier dépôt sur une étiquette déposée en Pickup Shop, où le colis rejoint le compte qui a commandé l'étiquette : « En transit »). | « Problème signalé » |

La ligne `We have X-rayed your parcel as part of our normal security procedures` correspond à un contrôle de sûreté courant et ne demande aucune démarche (« En transit » dans Peek), tandis que `There has been a delay with your delivery` est un avis de retard qui laisse l'étape précédente inchangée. Lorsqu'un colis DPD UK quitte le **Royaume-Uni** vers l'étranger, `Your parcel has left our sortation facility and is on it's onwards journey` (avec l'orthographe `it's` de DPD UK) marque la sortie du tri britannique, puis l'historique reprend les statuts anglais du partenaire local : `The parcel is in transit to its final destination` (« En transit »), `The parcel is undergoing customs clearance` et `Duties and Taxes paid` (« À la douane »), `The parcel has now cleared customs` et `The parcel has arrived at the delivery depot` (« En transit »), `The parcel is on the vehicle for delivery` (« En livraison ») et `The parcel has been delivered` (« Livré »). Plus de repères dans [que signifient les statuts de suivi](guide:tracking-statuses).

## Options Predict, relais Pickup au Royaume-Uni et douane vers l'Europe

Les modalités Predict, voisinage et Pickup ci-dessous s'appliquent aux livraisons effectuées au **Royaume-Uni** :

**Créneau Predict.** Au Royaume-Uni, DPD UK envoie par SMS et par e-mail un créneau de livraison d'une heure le matin même, une fois que le chauffeur a scanné les colis dans sa camionnette — en général avant midi (*usually by 12 noon*). Une carte en temps réel affine ensuite l'arrivée à une tranche de 15 minutes, et les SMS et e-mails de DPD UK indiquent le nom du chauffeur ainsi que votre rang dans sa tournée. Vous ne pouvez pas modifier l'heure du créneau lui-même.

**Modifier la livraison.** La veille au soir et le jour même, le lien reçu par SMS ou e-mail — ou l'application DPD — permet au Royaume-Uni de choisir une autre date, un voisin désigné, un lieu sûr (*safe place*) ou un commerce DPD Pickup Shop, ou encore de payer un surclassement avant 10 h 30, avant 12 h, l'après-midi, le samedi ou le dimanche. Si l'expéditeur a exigé une signature, certaines options ne sont pas proposées. Pour envoyer le colis à [une autre adresse](guide:change-delivery-address), contactez directement le vendeur.

**Absence à domicile.** Au Royaume-Uni, DPD UK tente automatiquement de remettre le colis à un voisin : ses conditions générales de transport définissent un voisin (*Neighbour*) comme une personne résidant ou travaillant dans un rayon de 50 mètres à pied de l'adresse de livraison (*within 50 metres walking distance of the Delivery Address*). Le chauffeur ne peut pas repasser le jour même. Avec votre numéro de référence et votre code postal sur `track.dpd.co.uk`, vous pouvez voir où a été laissée la carte *sorry we missed you* et programmer une nouvelle date, un lieu sûr, un voisin ou un retrait en relais Pickup ; DPD UK ne publie pas de nombre fixe de tentatives. Si le suivi indique « Livré » sans colis chez vous, vérifiez votre lieu sûr et vos voisins proches avant de consulter [colis indiqué livré mais non reçu](guide:delivered-not-received) ou [livraison manquée](guide:missed-delivery).

**Points DPD Pickup et consignes au Royaume-Uni.** DPD UK compte plus de 6 000 commerces Pickup au Royaume-Uni (notamment Sainsbury's, Post Office, Co-op, Morrisons, Argos et Matalan). Un colis redirigé vers un relais y est généralement disponible dès le lendemain. Pour le retirer, présentez deux justificatifs (*2 pieces of ID*) : une pièce d'identité avec photo et un justificatif d'adresse tel qu'une facture d'énergie, ainsi que le *DPD Pickup Pass* affiché sur la page de suivi. Les pages d'aide de DPD UK n'indiquent pas de durée de garde uniforme en boutique Pickup ; l'application DPD propose aussi la livraison en consigne (*Locker*).

**Frais d'importation au Royaume-Uni.** Pour un envoi entrant en Grande-Bretagne depuis l'étranger, la TVA britannique s'applique (sauf cadeaux jusqu'à 39 £) ainsi que d'éventuels droits de douane ; pour un achat de 135 £ ou moins (hors produits soumis à accise), le vendeur a déjà inclus la TVA dans le prix. S'il reste quelque chose à payer, le transporteur envoie une facture (payable en ligne sur `track.dpd.co.uk` sous *import charges*), et GOV.UK précise qu'il conserve normalement le colis environ 3 semaines (*about 3 weeks*) avant de le retourner à l'expéditeur faute de paiement.

**Colis expédiés du Royaume-Uni vers l'UE ou la Suisse.** La Grande-Bretagne étant hors UE, tout colis qui en vient passe en douane ; l'Irlande du Nord, elle, est traitée comme un territoire de l'UE pour la douane et la TVA sur les marchandises, selon la Commission européenne. Dans l'**Union européenne**, depuis le 1er juillet 2026, les achats en ligne hors UE d'une valeur intrinsèque maximale de 150 € paient un droit de douane de 3 € par article ; depuis le 1er novembre 2026 s'ajoutent 2 € de frais de traitement de l'Union par article, quelle que soit la valeur (un article regroupe les biens d'un même colis partageant classement tarifaire, désignation et, si requise, origine ; au-delà de 150 €, tarif douanier normal). Selon la douane française, en **France métropolitaine**, la TVA est due « dès le premier euro » ; en **Guadeloupe**, **Martinique** et **La Réunion**, elle s'applique si la valeur totale « dépasse 22 euros », et elle « n'est provisoirement pas applicable dans les départements de la Guyane et de Mayotte » (hors octroi de mer et droits au-delà de 150 €). En **Suisse**, selon l'OFDF, la TVA est de 8,1 % (2,6 % taux réduit), mais « les droits de douane inférieurs à 5 francs ne sont pas perçus » et « les montants d'impôt inférieurs à 5 francs ne sont pas perçus », hors frais du transporteur. Voyez [colis bloqué en douane](guide:customs).

## Colis DPD UK en retard, perdu, endommagé ou messages frauduleux

**Retards.** Selon les conditions générales de DPD UK, les délais annoncés sont indicatifs (*estimates only*). Au **Royaume-Uni**, la page [Service Disruption de DPD UK](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) liste les codes postaux touchés. Sans mouvement pendant plusieurs jours, lisez [pourquoi le suivi ne bouge plus](guide:tracking-not-updating) et contactez la boutique.

**Colis perdu ou endommagé.** Le contrat lie DPD UK à l'expéditeur (*Customer*), qui dépose la réclamation : il doit signaler perte ou avarie sous 14 jours après l'expédition (*within 14 days of the date of despatch*), fournir les preuves sous 28 jours après qu'elles lui ont été demandées et réclamer dans un délai d'un an (*within one year from the date of despatch*). Gardez emballage et photos pour le vendeur : voyez [colis perdu](guide:lost-parcel) et [colis endommagé](guide:damaged-parcel).

**Retour à l'expéditeur.** Selon sa politique de retour, DPD UK peut renvoyer un colis non livré gardé dans ses locaux après au moins 5 jours calendaires (*after a minimum of 5 calendar days*) ; les biens impossibles à restituer peuvent être détruits après 4 semaines (12 semaines pour des effets personnels). Voyez [colis retourné à l'expéditeur](guide:returned-to-sender).

> DPD UK ne demande jamais de frais de relivraison (*redelivery fee*) par lien dans un e-mail, SMS ou iMessage. Au Royaume-Uni, ses alertes arrivent par SMS (pas iMessage) sous `DPD UK`, `DPD Local` ou `DPDgroup UK`, ses e-mails finissent par `@dpd.co.uk`, `@dpdlocal.co.uk`, `@dpdgroup.co.uk` ou `@dpd.uk`, et ses liens n'utilisent que ses propres sites : `www.dpd.co.uk`, `www.dpdlocal.co.uk` ou `www.dpdgroup.co.uk`, et `track.dpd.co.uk` pour suivre un colis. Au **Royaume-Uni**, transférez les SMS suspects au `7726` et les e-mails à `report@phishing.gov.uk` ; voyez [SMS d'arnaque à la livraison](guide:delivery-scam-texts).

## Service client DPD UK et coordonnées

Au **Royaume-Uni** :

- **Téléphone :** `0121 275 0500`, lun.-ven. 8 h–18 h 30, sam. 8 h–16 h, dim. 9 h–15 h, hors *bank holidays* britanniques (5 à 10 min d'attente sur la page Contact ; objectif de 2 min 30 dans la charte). Préparez numéro de colis et code postal.
- **Chat :** sur la [page Contact de DPD UK](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) avec numéro de référence et code postal (annoncé `24/7` sur cette page ; lun.-ven. 8 h–18 h 30, sam. 8 h–16 h, dim. 9 h–15 h dans la charte des réclamations).
- **Application DPD :** appel et chat sous la carte de suivi ; un appel depuis l'application passe en tête de file.
- **Courrier :** `Customer Services, DPD UK, Roebuck Lane, Smethwick, B66 1BY`, Royaume-Uni (réponse visée sous 5 jours ouvrés, jusqu'à 28 jours pour un dossier complexe).

## Suivre un colis DPD UK avec Peek

[Peek](/) reconnaît un numéro DPD à 14 chiffres (avec ou sans son caractère de contrôle final), mais comme ce format est partagé entre plusieurs réseaux, Peek interroge les transporteurs compatibles pour savoir lequel détient le colis : **DPD UK en premier** lorsque le numéro commence par `1550` ou `1597`, sinon d'abord les transporteurs à 14 chiffres qui desservent votre pays. Lorsque DPD UK reconnaît le numéro, la ligne indique « a ce colis » et Peek le suit chez DPD UK. Si la mention affiche « pas encore trouvé · Peek continue de vérifier » ou retient un autre réseau, appuyez sur « Changer » (ou « Changer de transporteur ») et choisissez **DPD UK**. Coller un lien `track.dpd.co.uk` ou `tracking.dpd.co.uk` contenant `reference=` ou `parcelNumber=`, ou un lien `track.dpdlocal.co.uk` avec `reference=`, sélectionne d'emblée DPD UK. Si un numéro attribué à DPD UK reste inconnu de DPD UK et des services de suivi, Peek affiche d'abord « En attente de DPD UK », puis après deux jours « Après deux jours, le transporteur ne connaît toujours pas ce numéro. Vérifie-le, choisis un autre transporteur ou supprime le colis. »

Les scans du réseau DPD UK ne comportent pas de fuseau horaire : Peek les lit à l'heure du Royaume-Uni (`Europe/London`) et les convertit dans votre fuseau horaire. Sur peektracker.com et dans les applications iPhone et Android, le bas de l'historique précise votre fuseau (« Heures dans ton fuseau horaire : … »). Les libellés restent dans l'anglais de DPD UK, sans le prénom du chauffeur lors d'un passage manqué ni le nom d'un signataire après `delivered to …` ou `signed for by …`.

Peek n'affiche aucune ville ni aucun dépôt pour les scans de DPD UK, et ne lit ni la date estimée ni le créneau Predict d'une heure ; il écarte l'identifiant interne du colis, les références marchandes, les coordonnées du destinataire et les photos de livraison. Peek affiche le compte expéditeur sous « Expéditeur : … » tel que le nomme DPD UK, **sauf** lorsque l'historique montre que le colis a été déposé dans un DPD Pickup Shop : sur une telle étiquette (par exemple un retour boutique), le titulaire du compte n'est pas l'expéditeur et Peek masque ce champ. Sur un envoi international, Peek reste branché sur l'historique de DPD UK sans bascule automatique vers un autre transporteur : si le vendeur vous transmet un numéro local distinct, ajoutez-le séparément dans Peek.

Tant que vous gardez la page du colis ouverte ou, le jour de sa livraison, avec les notifications activées, Peek vérifie les mises à jour jusqu'à toutes les 10 minutes — et jusqu'à toutes les 2 minutes à partir de 6 h (heure britannique) le jour où DPD UK passe le colis en « En livraison » tant que le suivi direct de DPD UK répond (via les services de suivi seuls, toutes les heures, toutes les 3 heures après un jour sans mouvement et jusqu'à toutes les 15 minutes le jour de la livraison si le colis est ouvert ou que les notifications sont actives). En dehors de ces cas, ainsi qu'après deux jours sans nouveauté (ou 12 heures sur « Étiquette créée » ou « Pris en charge »), Peek vérifie toutes les heures. Vous pouvez choisir les alertes « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Pour modifier une livraison au Royaume-Uni, passez par le site ou l'application de DPD UK : Peek ne peut pas modifier vos instructions ni contacter DPD UK à votre place.

## Questions fréquentes sur le suivi DPD UK

### DPD UK fait-il partie de Royal Mail ?

Non. DPD UK est exploité par `DPDgroup UK Ltd` et appartient à Geopost, filiale colis du groupe français [La Poste](carrier:la-poste) (*La Poste Groupe*).

### Quels sont les délais de livraison de DPD UK au Royaume-Uni ?

Tout dépend du service choisi par le vendeur : au Royaume-Uni, `DPD NEXT DAY` vise le jour ouvré suivant avant la fin de journée (avec des options avant 10 h 30, avant midi, le samedi et le dimanche). Les îles Anglo-Normandes, l'île de Man et les îles Scilly sont desservies en deux jours en standard, et l'Irlande du Nord le lendemain par avion ou en deux jours par route et mer.

### Comment voir où se trouve mon chauffeur DPD UK ?

Pour une livraison au Royaume-Uni avec Predict, le SMS ou l'e-mail envoyé le matin de la tournée (en général avant midi) indique le nom du chauffeur et votre position dans sa file de livraison ; une carte en temps réel affine ensuite le créneau à 15 minutes.

### Combien de temps DPD UK garde-t-il un colis en relais Pickup ?

Les pages d'aide de DPD UK ne publient pas de durée unique de garde en boutique Pickup au Royaume-Uni : consultez l'avis de mise à disposition ou votre *DPD Pickup Pass* sur `track.dpd.co.uk`. D'après la politique générale de retour de DPD UK, un envoi non livré conservé dans ses locaux peut être renvoyé à l'expéditeur après un minimum de 5 jours calendaires.

:::sources
- [DPD UK: Help](https://www.dpd.co.uk/content/how-can-we-help/index.jsp) – numéro de colis et avis, créneau avant midi, voisin, lieu sûr, plus de 6 000 Pickup shops, pièces d'identité
- [DPD UK: Contact Us](https://www.dpd.co.uk/content/how-can-we-help/contact.jsp) – téléphone 0121 275 0500, horaires, attente, chat et appel depuis l'app
- [DPD UK: Predict & Follow My Parcel](https://www.dpd.co.uk/content/products_services/predict_fmp.jsp) – créneau d'une heure, carte à 15 min, chauffeur et options
- [DPD UK: Follow My Parcel](https://track.dpd.co.uk/) – recherche par référence ou avis et code postal, Pickup Pass, frais d'importation
- [DPD UK: Cyber Security](https://www.dpd.co.uk/content/about_dpd/phishing.jsp) – expéditeurs SMS, absence de frais par lien, domaines, 7726 et report@phishing.gov.uk
- [DPD UK: DPD Pickup](https://www.dpd.co.uk/content/products_services/dpd_pickup.jsp) – redirection vers un point DPD Pickup et retours
- [DPD UK: The DPD app](https://www.dpd.co.uk/dpdapp/) – préférences et consigne (Locker)
- [DPD UK: App FAQs](https://www.dpd.co.uk/lp/app/index.html) – colis DPD UK et DPD Local dans l'app
- [DPD UK: Standard Terms & Conditions of Carriage (PDF)](https://www.dpd.co.uk/pdf/dpd-stcc.pdf) – voisin à 50 m, délais indicatifs, réclamations (14 j, 28 j, un an)
- [DPD UK: Complaints Charter (PDF)](https://www.dpd.co.uk/pdf/dpd-complaints-charter.pdf) – horaires, adresse à Smethwick, réponse sous 5 à 28 jours
- [DPD UK: Return, Disposal and Destruction of Goods Policy](https://www.dpd.co.uk/return-disposal-of-goods-policy.jsp) – retour après 5 jours calendaires, conservation 4 ou 12 semaines
- [DPD UK: UK Delivery Services](https://www.dpd.co.uk/content/products_services/uk_delivery_services.jsp) – Next Day, 10:30, 12:00, week-end, îles et Irlande du Nord
- [DPD UK: Service Disruption](https://www.dpd.co.uk/content/products_services/uk_issues.jsp) – codes postaux britanniques touchés
- [GOV.UK: Tax and customs for goods sent from abroad](https://www.gov.uk/goods-sent-from-abroad/tax-and-duty) – facturation et garde d'environ 3 semaines
- [Geopost: About us](https://www.geopost.com/en/about-us/) – La Poste Groupe
- [Commission européenne : Champ d'application territorial de la TVA et de la douane](https://taxation-customs.ec.europa.eu/taxation/vat/vat-directive/how-does-vat-work/territorial-scope_en) – Irlande du Nord
- [Commission européenne : Réforme douanière de l'UE](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 € et frais de 2 €
- [Commission européenne : Orientations sur le droit de 3 € et les frais (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – calcul par article
- [Douane française : Vous achetez sur internet](https://www.douane.gouv.fr/demarche/vous-achetez-sur-internet) – TVA métropole et DOM
- [OFDF : Réception de lettres et de paquets](https://www.bazg.admin.ch/fr/reception-de-lettres-et-paquets) – TVA suisse et seuil de 5 francs
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – numéros, reconnaissance, statuts, fuseau horaire et expéditeur
:::
