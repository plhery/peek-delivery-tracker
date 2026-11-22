---
title: Suivi Quantium Solutions : numéros, statuts et assistance
description: Suivi d’un colis Quantium Solutions en Asie-Pacifique et à l’international : formats de numéros, sens des statuts, services, douane et contacts.
slug: suivi-quantium-solutions
published: 2026-11-22
updated: 2026-11-22
---

Quantium Solutions est une société de logistique e-commerce implantée dans 10 pays d’Asie-Pacifique. Elle transporte des commandes en ligne vers la région, au sein de la région et depuis celle-ci vers le reste du monde. Suis un colis sur la page [Track Your Shipments](https://track.quantiumworldwide.com/) de Quantium, ou colle le numéro dans la case ci-dessus. La boutique te transmet ce numéro dans son e-mail d’expédition ou sur ta page de commande.

## Numéro de suivi Quantium Solutions : à quoi il ressemble

L’outil de Quantium demande un « Tracking/Reference number » et en accepte plusieurs à la fois, un par ligne. Le code de Peek reconnaît deux formats de numéro Quantium, dont aucun n’appartient à Quantium seul. Avec des chiffres inventés :

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `4829173654` | Dix chiffres : dans le code de Peek, le format Quantium pour un envoi en Malaisie. Dix chiffres correspondent aussi à d’autres transporteurs, dont DHL : Peek ne peut donc pas identifier Quantium à partir du format seul. |
| `UQ482917367TH` | `UQ`, neuf chiffres et `TH` : dans le code de Peek, le format Quantium pour un envoi en Thaïlande. Il suit le format postal international, donc Peek indique Thailand Post. |
| `RA482917367SG` | Deux lettres, neuf chiffres et `SG` : un numéro Singapore Post. Le code de Peek relève que Quantium achemine certains colis sous des numéros SingPost. Peek indique Singapore Post. |

L’outil Quantium de Peek n’accepte que ces deux formes (dix chiffres ou format postal S10). Les espaces et les minuscules n’ont pas d’importance. Pour une référence d’un autre format, utilise directement le suivi de Quantium. Tu ne trouves pas ton numéro ? Consulte [où trouver un numéro de suivi](guide:find-tracking-number) et [formats de numéros de suivi](guide:tracking-number-formats).

## Signification des statuts de suivi Quantium Solutions

Chaque ligne du suivi Quantium comporte une étape principale et souvent une remarque indiquant le nom de l’étape chez le partenaire qui manipule le colis. Peek affiche l’étape principale, puis la remarque après deux-points lorsqu’elle apporte plus de précisions. Voici les lignes que Peek connaît, dans l’ordre habituel :

| Quantium indique | Ce que ça veut dire pour toi | Peek affiche |
| --- | --- | --- |
| `Shipment created` | La boutique a enregistré l’envoi. Quantium n’a pas encore le colis. | « Étiquette créée » |
| `Shipment received` | Quantium a reçu le colis. | « Pris en charge » |
| `Shipment under processing at origin hub` | En cours de tri dans un centre du pays d’expédition. | « En transit » |
| `Shipment despatched to transshipment hub` | En route vers un centre de transit où il change d’étape. | « En transit » |
| `DEPART ORIGIN` | A quitté son point de départ. | « En transit » |
| `Shipment received at transshipment hub: Arrive Hub` | Arrivé au centre de transit. | « En transit » |
| `Shipment despatched to overseas: Depart Hub` | A quitté le centre de transit vers ton pays. | « En transit » |
| `READY FOR DEPARTURE`, `FLIGHT DEPARTURE TO DESTINATION` | En attente de son vol, puis en vol vers ton pays. | « En transit » |
| `Shipment arrived at destination port: Arrive Destination` | Arrivé dans ton pays. | « En transit » |
| `IN TRANSIT: Customs Clearance Begins` | La douane de ton pays a commencé le dédouanement. | « À la douane » |
| `Shipment released from customs/ government check point: Customs Cleared` | La douane a libéré le colis. | « En transit » |
| `Shipment received at delivery branch: Handed Over for Delivery` | L’agence locale de livraison l’a réceptionné. | « En transit » |
| `IN TRANSIT` | En mouvement. Les scans de tri du partenaire dans ton pays apparaissent ainsi. | « En transit » |
| `Shipment out for delivery` | En cours de livraison. | « En livraison » |
| `Shipment delivered` | Livré. Lorsque la remarque indique qui a signé ou reçu le colis, Peek lit aussi ce nom. | « Livré » |

Une ligne inconnue de Peek s’affiche telle que Quantium l’écrit, et Peek déduit l’étape des mots employés lorsqu’il le peut. Pour un numéro inconnu, le suivi Quantium répond « No record! ». Plus d’infos dans [ce que signifient les statuts de suivi](guide:tracking-statuses).

**Heures.** Le suivi Quantium donne une date et une heure sans fuseau horaire, à l’horloge de l’opérateur qui a scanné le colis : le pays d’expédition pour les premières lignes, puis le partenaire dans ton pays. Deux lignes venant de pays différents ne peuvent donc pas être comparées heure par heure.

## Qui livre les colis Quantium Solutions

Quantium vend aux boutiques quatre services transfrontaliers :

| Service | Suivi | Canal de livraison | Délai de transit | Droits et taxes |
| --- | --- | --- | --- | --- |
| Q-Simple | aucun | postal | 4 à 17 jours | à la charge du destinataire (DDU) |
| Q-Smart | suivi | postal | 4 à 10 jours | à la charge du destinataire (DDU) |
| Q-Speed | suivi, prioritaire | postal | 3 à 7 jours | à la charge du destinataire (DDU) |
| Q-Sure | suivi | commercial | 2 à 5 jours | à la charge de l’expéditeur ou du destinataire (DDP ou DDU) |

Peek n’indique pas quel service utilise ton colis : demande à la boutique.

**En Asie.** Les services de dernier kilomètre propres à Quantium proposent la livraison à domicile, le retrait en bureau de poste, en consigne et dans plus de 3 000 points relais en Asie, avec paiement à la livraison en espèces ou par voie électronique. Sa livraison Regular prend 2 à 4 jours.

**En Australie.** La filiale australienne, Quantium Solutions (Australia) Pty Ltd, achemine les colis en Australie par transporteur — dont CouriersPlease — et par [Australia Post](carrier:australia-post) eParcel et express eParcel, comme l’indique sa page sur la surcharge carburant. Quantium cite aussi CouriersPlease parmi ses partenaires officiels. C’est le transporteur final qui fixe les tentatives de livraison et le lieu de garde d’un colis manqué.

Si personne n’était présent, lis [livraison manquée](guide:missed-delivery) et [colis non retirés](guide:uncollected-parcel).

## Douane et droits sur un colis Quantium

Sur Q-Simple, Q-Smart et Q-Speed, Quantium indique que les droits et taxes reviennent au destinataire : rendu droits non acquittés (DDU). Q-Sure peut être envoyé en DDP (payé par l’expéditeur) ou en DDU. Demande à la boutique ce qui s’applique à ta commande. Ce que tu dois payer dépend des règles de ton pays : voir [colis bloqué à la douane](guide:customs).

**Vers l’UE.** Les achats en ligne provenant hors UE supportent un droit de douane temporaire de 3 € par article dans les colis jusqu’à 150 €, depuis le 1er juillet 2026, ainsi que des frais de traitement de l’Union de 2 € par article quelle que soit la valeur, depuis le 1er novembre 2026. Un article désigne les marchandises d’un colis partageant classification tarifaire, description et, si exigé, origine. Quantium informe les entreprises qui expédient avec lui que les frais de 2 € s’appliquent à chaque ligne de déclaration douanière, sous IOSS comme sous DAP et quelle que soit la valeur de l’envoi, TVA en sus lorsque le pays de destination l’exige. Demande à la boutique si ton prix les incluait.

## Colis Quantium Solutions sans mise à jour, perdu ou retourné

**Uniquement `Shipment created`.** La boutique a enregistré l’envoi, mais Quantium n’a pas encore scanné le colis. Si la situation ne change pas pendant plusieurs jours, demande à la boutique s’il a bien été remis.

**Silencieux en transit.** Après quatre jours sans nouvelle ligne sur un colis en route, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») et indique « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Quantium Solutions. », avec le lien « Demande de l’aide à Quantium Solutions » vers son formulaire d’assistance. Quantium publie ses avis de perturbation sur sa page News (comme l’avis sur le fort épisode de brume de fumée en Malaisie et à Singapour du 7 octobre 2026). Son suivi peut aussi indiquer le numéro du partenaire sous « Tracking No. » : suis-le également chez ce partenaire. Voir [pourquoi un suivi s’arrête de se mettre à jour](guide:tracking-not-updating).

**Livré, mais introuvable.** Vérifie autour de ton logement et auprès des voisins, puis contacte la boutique et Quantium. Voir [livré mais non reçu](guide:delivered-not-received).

**Perdu ou abîmé.** Le site de Quantium ne donne aucune procédure de réclamation pour les destinataires. C’est la boutique qui est cliente de Quantium : demande-lui un remboursement ou un nouvel envoi. Voir [colis perdus](guide:lost-parcel) et [colis abîmés](guide:damaged-parcel).

**Retourné.** Quantium propose aux boutiques le retour à l’expéditeur. Voir [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et contact Quantium Solutions

Canaux publiés par Quantium :

- **En ligne :** le [formulaire d’assistance client](https://www.quantiumsolutions.com/contact-support/), pour le suivi d’un colis, l’aide générale et les remarques. Il demande ton nom, ton e-mail, le pays d’expédition et le numéro de suivi ; le champ « Customer ID » porte la mention « Corporate Customer only ». Quantium indique que son agent local répond le jour ouvré suivant.
- **Malaisie :** (603) 5569 6901, customerservice.my@quantiumworldwide.com.
- **Thaïlande :** (662) 712 2999, customerservice.th@quantiumworldwide.com.
- **Ailleurs :** la page [Contact Us](https://www.quantiumsolutions.com/contact-us/) liste des adresses d’assistance pour Hong Kong, l’Indonésie, Taïwan et le Vietnam. Pour Singapour, elle ne donne qu’une adresse commerciale, et aucun contact australien n’y figure. Aucun horaire d’ouverture n’est publié.

Pour une commande en ligne, adresse-toi d’abord à la boutique : c’est elle qui a réservé la livraison et qui est cliente de Quantium.

## Suivre un colis Quantium Solutions avec Peek

[Peek](/) ne peut pas reconnaître un numéro Quantium d’après sa seule forme. Colle-le tout de même : Peek suit plus de 4 500 transporteurs. Pour dix chiffres, Peek interroge les transporteurs utilisant ce format, puis les services de suivi (« Vérification des services de suivi… »). Pour un numéro finissant par `SG` ou `TH`, il indique Singapore Post ou Thailand Post (« Transporteur détecté »). Pour suivre les lignes propres à Quantium, appuie sur « Changer » et choisis Quantium Solutions ; sur un colis déjà ajouté, utilise « Changer de transporteur ». Si tu choisis Quantium pour un numéro d’un autre format, Peek affiche « n’émet pas de numéros comme celui-ci ».

Un lien vers le suivi de Quantium contenant `trackno=` fonctionne aussi : Peek en extrait le numéro et identifie Quantium Solutions immédiatement.

Peek affiche le statut de Quantium, chaque ligne dans les mots de Quantium (tableau ci-dessus), le pays de destination, ainsi que le nom du destinataire (sous « Destinataire »), son adresse (hors ligne téléphonique) et le nom du signataire lorsqu’une remarque de livraison l’indique. Il laisse de côté la référence client, le numéro du partenaire, les données de l’expéditeur et le lieu d’origine. Quantium ne donnant ni lieu pour chaque ligne, ni date de livraison prévue, ni poids, ni preuve de livraison, Peek n’en affiche pas. « Ouvrir le site Quantium Solutions » ouvre ton numéro sur le suivi Quantium.

Les heures de Quantium ne portant pas de fuseau horaire, Peek les lit toutes sur une seule horloge : celle du pays de destination pour un envoi vers Singapour, la Malaisie, la Thaïlande ou l’Australie (heure de Sydney pour l’Australie), sinon l’heure de Singapour. Il affiche chaque horaire dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom), si bien qu’une ligne scannée dans un autre pays peut être décalée de quelques heures.

Lorsque le numéro suit le format postal S10 avec un chiffre de contrôle valide et que Quantium indique le pays de destination, Peek interroge aussi la poste de ce pays avec le même numéro, comme [Australia Post](carrier:australia-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), An Post, [Canada Post](carrier:canada-post) ou NZ Post. Il suit le colis là-bas dès que cette poste affiche de nouveaux scans, tout en conservant l’historique de Quantium. Cela ne s’applique pas à tous les pays (par exemple pas pour Singapour, la Malaisie, la Thaïlande, Hong Kong ou le Vietnam), ni aux numéros à dix chiffres.

Tant que tu as le colis ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement Quantium jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes dès 6 h, heure de Singapour, le jour où Quantium scanne le colis « En livraison », tant que le suivi de Quantium répond ; sinon, et après deux jours sans nouveauté (12 heures tant qu’il reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Il peut t’avertir selon ton choix : « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Il ne peut pas contacter Quantium ni la boutique pour toi.

## Questions sur le suivi Quantium Solutions

### Quantium Solutions et Quantium Worldwide sont-ils la même société ?

Oui : son suivi et ses adresses e-mail d’assistance utilisent le domaine `quantiumworldwide.com`, et Peek la reconnaît aussi sous les noms Quantium ou Quantium Solutions International.

### Pourquoi Peek affiche-t-il Singapore Post ou Thailand Post pour mon numéro Quantium ?

Parce que ton numéro suit le format postal international, dont les deux dernières lettres désignent une poste : `SG` pour Singapore Post, `TH` pour Thailand Post. Pour voir les lignes propres à Quantium, appuie sur « Changer » et choisis Quantium Solutions.

### Que signifie « Customs Clearance Begins » ?

La douane de ton pays a commencé le dédouanement du colis, et Peek affiche « À la douane ». Quand la douane le libère, la ligne devient `Shipment released from customs/ government check point: Customs Cleared`. Sur un service DDU, les éventuels droits et taxes sont à ta charge : voir [colis bloqué à la douane](guide:customs).

### Combien de temps met Quantium Solutions pour livrer ?

Quantium annonce 2 à 5 jours pour Q-Sure et jusqu’à 17 jours pour Q-Simple (tableau ci-dessus). En Asie, sa livraison Regular prend 2 à 4 jours.

### Puis-je suivre un colis Q-Simple ?

Pas en cours de route. Quantium vend Q-Simple comme son service économique sans suivi : ne compte donc pas sur des scans et demande à la boutique quand il a été expédié.

:::sources
- [Quantium Solutions: Track Your Shipments](https://track.quantiumworldwide.com/) – outil de suivi, colonne « Tracking No. », « No record! »
- [Quantium Solutions: Home](https://www.quantiumsolutions.com/) – présence dans 10 pays d’Asie-Pacifique
- [Quantium Solutions: About Us](https://www.quantiumsolutions.com/about-us/) – activité de Quantium, CouriersPlease parmi ses partenaires
- [Quantium Solutions: Cross Border Shipping](https://www.quantiumsolutions.com/cross-border-shipping/) – les quatre services, retour à l’origine
- [Quantium Solutions: Last Mile Delivery](https://www.quantiumsolutions.com/last-mile-delivery/) – options de livraison en Asie
- [Quantium Solutions: Australia Fuel Surcharges](https://www.quantiumsolutions.com/australia-fuel-surcharges/) – CouriersPlease, Australia Post eParcel
- [Quantium Solutions: Service Impact Notice (haze)](https://www.quantiumsolutions.com/news/13/) – retards liés à la brume de fumée
- [Quantium Solutions: Service Update (EU customs handling fee)](https://www.quantiumsolutions.com/news/14/) – frais de 2 € par ligne de déclaration
- [Quantium Solutions: Customer Support Form](https://www.quantiumsolutions.com/contact-support/) – champs du formulaire, délai de réponse
- [Quantium Solutions: Contact Us](https://www.quantiumsolutions.com/contact-us/) – téléphones et adresses e-mail
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 €, frais de traitement de 2 €
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – définition d’un article
- [Universal Parcel Scraper: Quantium Solutions](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/quantium-solutions) – formats de numéros, liens, libellés, horloges, champs lus
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection, horloge utilisée pour Quantium, relais vers la poste de destination
:::
