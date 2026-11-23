---
title: Suivi CouriersPlease : numéros, statuts et aide
description: Suivez un colis CouriersPlease en Australie : format du numéro d’envoi, statuts de suivi, points relais HUBBED, relivraison, Delivery Choices et réclamations.
slug: suivi-couriersplease
published: 2026-11-23
updated: 2026-11-23
---

CouriersPlease (Couriers Please Pty Limited, filiale du groupe FMH) est un transporteur australien qui assure la livraison de colis dans les grandes villes et les zones régionales d’Australie par l’intermédiaire de franchisés locaux (Franchise Partners). Pour suivre un colis, saisissez son numéro d’envoi sur la [page de suivi CouriersPlease](https://www.couriersplease.com.au/tools-track) — qui accepte jusqu’à 10 numéros séparés par des virgules — ou collez-le dans le champ ci-dessus. Le numéro vous est communiqué par la boutique ou l’expéditeur qui a réservé le transport.

## Numéro de suivi CouriersPlease : à quoi ressemble-t-il ?

CouriersPlease parle de numéro d’envoi (*consignment number*) ou de numéro de suivi. La page de relivraison indique qu’il peut commencer par `CP` et compter jusqu’à 20 caractères. Pour CouriersPlease, Peek accepte de 8 à 30 lettres et chiffres (dont au moins un chiffre), ce qui couvre les numéros d’envoi, les étiquettes d’articles, les coupons prépayés et les codes SSCC imprimés par certains expéditeurs. Tous les numéros CouriersPlease ne commencent donc pas par `CP`.

| Numéro (inventé) | Ce que c’est |
| --- | --- |
| `CPABC4829173640` | Numéro d’envoi CouriersPlease typique : `CP`, des lettres, puis des chiffres. |
| `CPA48291736405` | Autre format CouriersPlease commençant par `CP`, légèrement plus court. |
| `CP482917367AU` | Pas CouriersPlease : deux lettres, neuf chiffres et `AU` correspondent à un envoi postal international, comme ceux d’[Australia Post](carrier:australia-post). |

**Où le trouver.** Regardez d’abord dans l’e-mail de confirmation de commande envoyé par la boutique. Les notifications officielles de CouriersPlease viennent de `noreply@couriersplease.com.au` par e-mail (uniquement si l’expéditeur a transmis votre adresse) et sous le nom d’expéditeur `Courierspls` par SMS. Si un avis de passage papier a été déposé, il comporte un numéro de carte de relivraison (*redelivery card number*), également accepté sur le formulaire de relivraison. Notre guide pour [trouver un numéro de suivi](guide:find-tracking-number) recense d’autres pistes si vous ne retrouvez aucun numéro.

**Numéro invalide ou aucun résultat ?** Vérifiez l’absence de faute de frappe et confirmez auprès du vendeur que le colis a bien été confié à CouriersPlease. Tant qu’aucun événement de scan n’existe dans le système pour ce numéro, la page de suivi le signale comme introuvable ou invalide.

## Statuts de suivi CouriersPlease expliqués

Dans son [guide de réception](https://www.couriersplease.com.au/faqs), CouriersPlease indique envoyer des mises à jour à cinq grandes étapes : annonce du colis à collecter, prise en charge chez l’expéditeur, arrivée au dépôt, sortie en livraison, puis livraison ou dépôt en point relais. Sur la page de suivi, les libellés apparaissent en anglais ; voici les statuts gérés par le suivi CouriersPlease (et reconnus dans [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper)), dans l’ordre habituel :

| Statut CouriersPlease | Ce que cela signifie pour vous |
| --- | --- |
| `Shipment information received` | L’expéditeur a créé l’envoi. CouriersPlease n’a peut-être pas encore le colis entre les mains. |
| `Your parcel has been picked up` | CouriersPlease a collecté le colis chez l’expéditeur. |
| `Pickup attempt was unsuccessful. Awaiting rebook confirmation by sender` | La tentative de collecte chez l’expéditeur a échoué ; le vendeur doit reprogrammer l’enlèvement. |
| `Arrived at facility for processing` | Le colis est arrivé dans un centre de traitement CouriersPlease. |
| `Parcel requires further processing` | Le colis fait l’objet d’un traitement complémentaire dans le centre ; il reste bien dans le réseau. |
| `Processed at delivery centre` | Traité au centre de distribution. |
| `In transit to delivery centre` | En acheminement vers un centre de distribution (la ligne précise souvent les deux villes entre parenthèses). |
| `Arrived at delivery centre` | Arrivé au centre de distribution ; aucune action requise. |
| `In transit to regional delivery centre` | Transféré vers un partenaire de livraison régional ou sur une liaison régionale. |
| `Onboard for delivery` | Chargé dans le véhicule du livreur ou du franchisé pour la tournée du jour. |
| `Delivery rescheduled for next business day` | De retour au dépôt sans avoir été livré ; reprogrammé au jour ouvré suivant. |
| `Delivery attempted – Unsafe to leave – Sent to a collection point` | Personne n’a pu réceptionner le colis et aucun endroit sûr n’était disponible ; il part vers un point relais. |
| `Delivery attempted – Signature required – Redirected to a collection point` | Une signature était exigée et personne n’était présent ; redirection vers un point relais. |
| `Unable to deliver` | La livraison n’a pas pu aboutir et demande une vérification auprès du support. |
| `Processing for collection at a nearby collection point` | Le colis est arrivé au point relais, mais n’est pas encore prêt pour le retrait. |
| `Pickup at Collection Point – Ready for collection` | Prêt à être retiré au point relais avec votre code PIN. |
| `Delivered with Authority to Leave (ATL)` | Déposé à votre adresse dans un endroit sûr, sans signature. |
| `Delivered` | Livré à l’adresse prévue. |
| `Parcel has been collected from` | Retiré au point relais nommé à la suite du libellé. |
| `Collection period expired – Return to sender in progress` | Délai de retrait dépassé ; le colis repart chez l’expéditeur. |

Le statut `Shipment information received` est celui qui crée le plus de doutes : l’étiquette et les données d’envoi existent, mais le colis peut encore attendre chez le commerçant. Si rien ne bouge, demandez d’abord au vendeur de confirmer la remise au chauffeur.

Certaines lignes — comme `Delivery rescheduled` ou celles qui commencent par `Delivery instructions –` (nouvelle date, point relais, voisin, autorisation de dépôt ou nouvelle adresse) — enregistrent simplement une instruction ou une nouvelle date prévue sans faire avancer physiquement le colis.

## Livraison, points relais HUBBED, relivraison et Delivery Choices

**Délais indicatifs.** Le [guide de réception](https://www.couriersplease.com.au/faqs) annonce 1 à 3 jours ouvrés entre grandes métropoles, quelques jours de plus d’un bout à l’autre du pays (par exemple d’est en ouest), davantage pour les zones régionales ou isolées, et une semaine supplémentaire pendant les pics comme Noël. Ces délais sont toujours indicatifs et englobent la préparation de la commande, le passage en dépôt et l’éventuel délai de relivraison ou de retrait ; ils peuvent varier selon la météo, le trafic ou un imprévu.

**Livraison et absence (Authority to Leave).** Lorsque le colis est en tournée, le chauffeur frappe ou sonne à votre porte, sauf si l’expéditeur a demandé une livraison sans contact. Selon les [Conditions générales de transport](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) (clause 5.3) et la page [Delivery Choices](https://www.couriersplease.com.au/delivery-choices), les colis CouriersPlease sont traités par défaut en mode *Authority to Leave* (ATL) : si quelqu’un est présent, le chauffeur saisit `SIG` et le nom du réceptionnaire sur son terminal ; en cas d’absence, il cherche un endroit sûr pour déposer le colis. S’il n’existe aucun emplacement sécurisé (appartement, résidence ou porte très exposée) ou si l’expéditeur exige une signature, le colis est orienté vers un point relais voisin — sauf si le colis dépasse 10 kg ou 100 cm de dimensions cubiques (environ la taille d’un bagage cabine), auquel cas il vous est demandé de programmer une relivraison. Dans les zones régionales, CouriersPlease précise que les colis sont systématiquement déposés en lieu sûr (ATL).

**Points relais HUBBED.** CouriersPlease s’appuie sur le réseau HUBBED, qui compte plus de 3 000 points relais aux horaires étendus en Australie ; vous pouvez les chercher par banlieue ou code postal sur le [localisateur de points relais](https://www.couriersplease.com.au/resources/find-a-collection-point) (avec filtres *Drop Off*, *Redirections* et *Failed deliveries*). Si votre colis est acheminé vers un point relais et que le vendeur a fourni votre adresse e-mail, CouriersPlease vous envoie un e-mail sous 24 heures ; sinon, un avis papier est déposé. D’après la clause 5.4 des Conditions générales de transport, la livraison est réputée effectuée lorsque CouriersPlease vous envoie le code PIN de retrait : vous disposez de **7 jours** après l’envoi du PIN pour récupérer le colis, faute de quoi il est conservé au dépôt le plus proche pendant **5 jours supplémentaires** avant d’être renvoyé aux frais du client. Si vous n’avez pas reçu votre code PIN, demandez-le sur la [page Get PIN de HUBBED](https://hubbed.com/getpin/) (pour vous-même, votre entreprise ou un tiers).

**Relivraison.** Pour organiser une nouvelle présentation, rendez-vous sur la [page de relivraison](https://www.couriersplease.com.au/book/redelivery) avec votre numéro d’envoi ou votre numéro de carte de relivraison. Le guide de réception précise que si votre colis a été déposé en point relais, vous pouvez demander une relivraison dans un délai de 6 jours. Le centre d’aide de CouriersPlease dit au contraire qu’un colis déjà envoyé en point relais ne peut pas être relivré à votre adresse et doit y être retiré : en cas de doute, demandez au support avant de réserver.

**Delivery Choices.** Si l’expéditeur a renseigné à la fois votre e-mail et votre numéro de mobile lors de la réservation, CouriersPlease vous envoie un lien sécurisé offrant cinq options sur la page [Delivery Choices](https://www.couriersplease.com.au/delivery-choices) :

- exiger une signature à la livraison (en cas d’absence, le colis part en point relais ou retourne au dépôt) ;
- rediriger vers un point relais proche aux horaires étendus ;
- autoriser la remise à un voisin dans un rayon de trois maisons de chaque côté ;
- modifier l’adresse de livraison ;
- choisir une autre date dans les trois jours suivant la date de livraison initiale.

## Colis en retard, manquant ou endommagé

**Retard ou suivi bloqué.** CouriersPlease indique que la plupart des colis en retard arrivent dans les 24 à 48 heures ouvrées suivant l’estimation initiale. Consultez les alertes de service sur le site de CouriersPlease ; si la situation paraît anormale, ouvrez le chat de l’assistant virtuel (*Virtual Assistant*, icône en bas à droite sur couriersplease.com.au), choisissez *Assistance with a Delivery* et déposez votre demande une seule fois — envoyer plusieurs demandes ralentit le traitement. Consultez aussi notre guide quand un [suivi ne se met plus à jour](guide:tracking-not-updating).

**Indiqué livré mais introuvable.** Vérifiez les endroits abrités autour de votre entrée, auprès des occupants du logement, d’un gardien ou du local courrier, puis signalez le problème au vendeur et à CouriersPlease via l’assistant virtuel. Voir également [colis indiqué livré mais non reçu](guide:delivered-not-received) et [colis perdu](guide:lost-parcel).

**Colis endommagé ou perdu et garantie Parcel Protect.** Sur les envois nationaux en Australie, la [couverture domestique Parcel Protect](https://www.couriersplease.com.au/parcel-protect-domestic-cover) inclut automatiquement une garantie gratuite jusqu’à 1 000 AUD, mais exclut les 100 premiers AUD de perte, les colis livrés sous *Authority to Leave* (ATL), l’emballage insuffisant et plusieurs catégories de biens (objets de grande valeur, espèces, bijoux, cartes cadeaux, tableaux avec verre, miroirs, fleurs, plantes, denrées périssables, effets personnels et appareils électroniques, marchandises dangereuses). Toute réclamation écrite pour perte ou avarie doit être notifiée dans les **14 jours ouvrés** suivant la livraison ou la date à laquelle le colis aurait dû être livré (la première des deux dates). Si l’envoi a été réservé via un courtier tiers (*third party booking agent*), la réclamation doit passer par cet intermédiaire. En tant que destinataire, commencez par contacter la boutique : c’est l’expéditeur titulaire du compte qui dépose le dossier. Hors Parcel Protect, la clause 7.3 des Conditions générales de transport plafonne la responsabilité standard au réacheminement, à son coût ou à 100 AUD (sous réserve des droits prévus par l’Australian Consumer Law). Plus de conseils dans [colis endommagé](guide:damaged-parcel).

## Contacter CouriersPlease et éviter les arnaques

- **Assistant virtuel :** cliquez sur l’icône de chat en bas à droite sur [couriersplease.com.au](https://www.couriersplease.com.au/faqs), puis sélectionnez *Assistance with a Delivery* (ou tapez `feedback` pour envoyer un retour). Ne créez qu’une seule demande par colis.
- **Centre d’aide :** accessible depuis le menu *Help centre* du site couriersplease.com.au.
- **Siège social :** Couriers Please Pty Limited (ABN 76 006 144 734), Rosehill Industrial Estate Building C South, Level 1, 5–7 Shirley Street, Rosehill, NSW 2142, Australia.

> Selon la page [protection contre les arnaques](https://www.couriersplease.com.au/scam-protection), les e-mails officiels de CouriersPlease proviennent de `noreply@couriersplease.com.au` et ses SMS s’affichent sous le nom `Courierspls`. CouriersPlease ne vous demandera jamais de modifier des coordonnées bancaires, de payer des frais pour débloquer un colis, de saisir vos identifiants ou de répondre `Y` pour activer un lien (méfiez-vous des faux domaines comme `couriersplease.addliik.com` ou `no-reply@wufoo.com`). En cas de doute, supprimez le message, suivez votre colis directement sur couriersplease.com.au et signalez l’arnaque à CouriersPlease ainsi qu’à Scamwatch.

## Suivre un colis CouriersPlease avec Peek

[Peek](/) reconnaît immédiatement CouriersPlease pour les numéros au format `CP` suivi d’une lettre, de cinq à dix caractères alphanumériques et de six chiffres (comme `CPABC4829173640` ou `CPA48291736405`), ainsi que pour les liens de suivi couriersplease.com.au/tools-track contenant `no=` ou `trackingCode=`. Aucun compte, code postal ni numéro de téléphone n’est nécessaire. Si votre numéro CouriersPlease ne commence pas par `CP`, collez le lien de suivi ou choisissez CouriersPlease avec « Changer » en l’ajoutant, ou « Changer de transporteur » sur un colis déjà ajouté.

Peek lit directement le suivi officiel de CouriersPlease et affiche chaque scan dans le libellé anglais du transporteur, avec l’heure que donne CouriersPlease (heure de Sydney, même hors de Nouvelle-Galles du Sud), l’État australien lorsqu’il est indiqué et la date de livraison estimée (*Delivery ETA*) dès que le colis a dépassé le stade d’annonce initiale (et jusqu’à la livraison ou au retour). Peek retire le code HTML et les liens de support ajoutés dans certains messages, et n’affiche ni l’adresse du point relais, ni le code PIN HUBBED, ni la photo de preuve de livraison.

Peek interroge directement le suivi de CouriersPlease jusqu’à toutes les 10 minutes quand vous gardez le colis ouvert ou le jour de la livraison avec alertes activées, et toutes les 2 minutes à partir de 6 h, heure de Sydney, le jour où CouriersPlease scanne le colis comme sorti en livraison (`Onboard for delivery`), tant que son suivi répond ; sinon toutes les heures. Peek compte ce jour à l’heure de Sydney, celle des scans de CouriersPlease : à Perth, ces vérifications commencent donc à 3 ou 4 h et s’arrêtent à 21 ou 22 h. Après 12 heures au stade « Étiquette créée » ou « Pris en charge », ou après deux jours sans nouveau scan, Peek repasse à une vérification par heure même si l’écran reste ouvert, puis toutes les 6 heures après sept jours encore au stade « Étiquette créée ». Si Peek passe par des services de suivi en relais, il vérifie toutes les heures, toutes les 3 heures après un jour sans mise à jour, et jusqu’à toutes les 15 minutes le jour de la livraison.

Si un colis à « Étiquette créée », « Pris en charge » ou « En transit » reste quatre jours sans nouveau scan, Peek le signale avec le jour de ce dernier scan (par exemple « Aucune mise à jour depuis lun 12 oct »), affiche « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger CouriersPlease. » et propose le lien « Demande de l’aide à CouriersPlease » vers le centre d’aide de CouriersPlease. Au-delà de trois semaines sans nouveauté, Peek affiche : « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger CouriersPlease. »

Vous pouvez choisir de recevoir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek ne peut pas réserver de relivraison, demander de code PIN HUBBED ni déposer de réclamation Parcel Protect à votre place : pour ces démarches, passez par CouriersPlease ou par la boutique.

## Questions fréquentes sur le suivi CouriersPlease

### Combien de temps prend une livraison CouriersPlease ?

Le guide de réception annonce 1 à 3 jours ouvrés entre grandes métropoles australiennes, quelques jours de plus d’une côte à l’autre, davantage pour les zones régionales ou éloignées et une semaine supplémentaire en période de pointe comme Noël. Ce sont des estimations incluant le traitement au dépôt.

### Pourquoi mon suivi CouriersPlease ne se met-il pas à jour ?

Un colis peut rester sans scan pendant un acheminement inter-États, un transfert vers un partenaire régional ou un traitement en centre. CouriersPlease précise que la plupart des colis retardés arrivent dans les 24 à 48 heures ouvrées suivant l’estimation initiale ; au-delà, contactez l’assistant virtuel sur couriersplease.com.au.

### Comment fonctionnent les points relais HUBBED et le code PIN ?

Lorsque le chauffeur ne peut pas laisser le colis en lieu sûr ou qu’une signature est exigée (et que le colis ne dépasse pas 10 kg ni 100 cm cubiques), il le dépose dans l’un des 3 000 points relais HUBBED. CouriersPlease vous envoie le code PIN et les instructions ; d’après les Conditions de transport, vous avez 7 jours après l’envoi du PIN pour le retirer, après quoi il reste 5 jours au dépôt avant retour. Si vous n’avez pas reçu le PIN, demandez-le sur hubbed.com/getpin/.

### Que faire si mon colis CouriersPlease est endommagé ou laissé en Authority to Leave ?

Prévenez immédiatement la boutique qui a expédié la commande. Les réclamations écrites au titre de Parcel Protect doivent être adressées sous 14 jours ouvrés (avec franchise de 100 AUD et plafond de 1 000 AUD), mais Parcel Protect ne couvre pas les colis livrés avec *Authority to Leave* (ATL).

:::sources
- [CouriersPlease : Track A Parcel Delivery](https://www.couriersplease.com.au/tools-track) – suivi jusqu’à 10 numéros séparés par des virgules
- [CouriersPlease : Guide to receiving](https://www.couriersplease.com.au/faqs) – cinq étapes de suivi, délais indicatifs, seuils 10 kg / 100 cm, délai de 6 jours pour la relivraison, 24 à 48 heures ouvrées en cas de retard, assistant virtuel
- [CouriersPlease : Book A Parcel Redelivery](https://www.couriersplease.com.au/book/redelivery) – numéro d’envoi ou carte de relivraison, préfixe CP, jusqu’à 20 caractères
- [CouriersPlease : How do I arrange a redelivery?](https://support.couriersplease.com.au/hc/en-au/articles/31312747333785-How-do-I-arrange-a-redelivery) – pas de relivraison pour un colis déjà envoyé en point relais
- [CouriersPlease : Find a Collection Point](https://www.couriersplease.com.au/resources/find-a-collection-point) – recherche par banlieue ou code postal et filtres de points relais
- [CouriersPlease : Delivery Choices](https://www.couriersplease.com.au/delivery-choices) – ATL par défaut, cinq options de livraison, règle régionale ATL
- [CouriersPlease : Parcel Protect Domestic Cover](https://www.couriersplease.com.au/parcel-protect-domestic-cover) – plafond 1 000 AUD, exclusion des 100 premiers AUD et de l’ATL, délai de 14 jours ouvrés, réclamations via courtier tiers
- [CouriersPlease : Terms and Conditions of Carriage](https://www.couriersplease.com.au/media/Terms_and_Conditions_of_Carriage_12_October_2023_ed4c2f9d36.pdf) – clauses 5.3 (ATL / SIG), 5.4 (7 jours en point relais après PIN + 5 jours au dépôt), 7.3 (plafond de responsabilité) et adresse sociale
- [CouriersPlease : Scam protection](https://www.couriersplease.com.au/scam-protection) – expéditeurs officiels noreply@couriersplease.com.au et Courierspls, signes d’hameçonnage, Scamwatch
- [CouriersPlease : Our Story](https://www.couriersplease.com.au/our-story) – groupe FMH, réseau de Franchise Partners, plus de 3 000 points relais HUBBED
- [HUBBED : Get PIN](https://hubbed.com/getpin/) – demande de code PIN de retrait en point relais
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – détection des numéros et liens CouriersPlease, table des statuts et champs lus par Peek
:::
