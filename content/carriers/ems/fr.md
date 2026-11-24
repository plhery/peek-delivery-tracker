---
title: Suivi EMS : numéros S10, statuts et aide postale
description: Suis un colis international EMS (Express Mail Service) : numéros S10 de 13 caractères en E, statuts douane et livraison UPU, délais et contacts postaux.
slug: suivi-ems
published: 2026-11-24
updated: 2026-11-24
---

**EMS** (*Express Mail Service*) est le service postal express international dédié aux documents et aux marchandises. L’**Union postale universelle (UPU)** a créé la **Coopérative EMS** ([ems.post](https://www.ems.post/en)) pour le développer ; la Coopérative comptait 179 membres en juin 2026. Plutôt que d’exploiter ses propres avions ou camionnettes, EMS relie les opérateurs postaux nationaux désignés dans le monde entier — comme [Japan Post](carrier:japan-post), [China Post](carrier:china-post), [Korea Post](carrier:korea-post), [USPS](carrier:usps), [Royal Mail](carrier:royal-mail), [La Poste](carrier:la-poste), [Poste Italiane](carrier:poste-italiane) et [Australia Post](carrier:australia-post) — afin que documents et marchandises express circulent sous un identifiant normalisé de 13 caractères. Tu peux vérifier un envoi sur le suivi mondial EMS à [items.ems.post](https://items.ems.post/) ou coller ton numéro d’envoi EMS dans le champ ci-dessus.

## Numéros de suivi EMS (format UPU S10 de 13 caractères)

Chaque envoi EMS porte un code-barres UPU S10 de 13 caractères commençant par la lettre **`E`** (pour Express), suivi d’une seconde lettre de service, de 8 chiffres de série, d’un chiffre de contrôle S10 et du code ISO à 2 lettres du pays d’origine :

| Format (inventé) | De quoi il s’agit |
| --- | --- |
| `EA482917367JP` | Envoi EMS de 13 caractères expédié du Japon (préfixe `EA` + 9 chiffres avec chiffre de contrôle S10 valide + `JP`). |
| `EE739184627CN` | Envoi EMS de 13 caractères expédié de Chine (préfixe `EE` + 9 chiffres avec chiffre de contrôle S10 valide + `CN`). |

Les deux dernières lettres désignent toujours l’**opérateur postal d’origine**, jamais le pays de destination. Un colis envoyé en EMS de Tokyo vers Paris se termine par `JP` pendant tout son trajet, même lorsque [La Poste](carrier:la-poste) prend le relais pour la livraison finale en France. Les codes postaux ne commençant pas par `E` (comme les lettres recommandées `RR...` ou les colis ordinaires `CP...`) sont rejetés par le suivi EMS comme n’étant pas des envois EMS. Voir les [formats de numéros de suivi](guide:tracking-number-formats).

## Ce que signifient les statuts de suivi EMS

Le tableau public de suivi de la Coopérative EMS sur [items.ems.post](https://items.ems.post/) traduit les événements EDI des opérateurs postaux participants en libellés anglais normalisés :

| EMS indique | Ce que cela signifie pour toi | Peek affiche |
| --- | --- | --- |
| `Posted` | Déposé au guichet ou collecté par l’opérateur postal d’origine. | « Pris en charge » |
| `Arrived at export office`, `Departed from export office` | Traité au bureau d’échange international du pays d’origine et expédié à l’étranger. | « En transit » |
| `Presented to export customs and security`, `Held for export customs inspection` | En cours de contrôle douanier export ou de sûreté aérienne dans le pays d’origine. | « À la douane » |
| `Released from export customs and security` | Contrôles export terminés ; prêt pour l’acheminement international. | « En transit » |
| `Export cancelled` | L’expédition export a été annulée ou renvoyée avant de quitter le pays d’origine ; contacte l’expéditeur. | « Problème signalé » |
| `Arrived at destination import office`, `Departed from destination import office` | Arrivé au centre d’échange international du pays de destination ou reparti vers le tri national. | « En transit » |
| `Held at destination import office`, `Presented to import customs` | Retenu au bureau d’échange frontalier ou présenté à la douane d’importation du pays de destination. | « À la douane » |
| `Released from import customs` | Dédouané à l’importation et remis au réseau postal national. | « En transit » |
| `Arrived at sorting center`, `Arrived at post office` | En transit dans les centres de tri nationaux de la poste de destination ou arrivé au bureau de poste distributeur. | « En transit » |
| `Out for delivery` | En cours de tournée avec le facteur local ou le livreur EMS pour la livraison finale. | « En livraison » |
| `Delivery attempted` | Une tentative de livraison a échoué ; vérifie un avis de passage ou une notification du bureau de poste local. | « Tentative de livraison » |
| `Returned to sender` | Renvoyé vers le pays d’origine après échec de livraison, refus ou rejet en douane. | « Retourné à l’expéditeur » |
| `Delivered` | Livré au destinataire ou dans un point de retrait autorisé par l’opérateur de destination. | « Livré » |

## Relais internationaux et douane avec EMS

Comme EMS est un réseau coopératif de postes nationales recensées dans l’[annuaire des opérateurs EMS](https://www.ems.post/en/global-network/ems-operators), deux organismes postaux distincts prennent en charge chaque envoi transfrontalier :

- **Poste d’origine et poste de destination :** l’opérateur postal d’origine gère le dépôt, le dédouanement export et l’envoi international ; dès qu’apparaît `Arrived at destination import office`, c’est ton opérateur postal national (ou sa filiale express, comme Parcelforce au Royaume-Uni) qui gère le dédouanement import, les tentatives de livraison, la reprogrammation et le délai d’instance en bureau de poste.
- **Consulter EMS et ta poste locale :** le flux mondial EMS diffuse des jalons EDI normalisés, tandis que le suivi propre de ta poste de destination ajoute souvent des détails nationaux plus riches : lien de paiement des droits de douane, adresse du bureau de retrait, créneau de livraison ou demande de nouvelle présentation.
- **Droits de douane et TVA à l’importation :** les colis EMS transfrontaliers sont soumis aux règles douanières du pays de destination, à la TVA à l’importation, aux frais de dédouanement postal et aux droits de douane (y compris les frais d’importation hors UE dans l’Union européenne). Si le suivi s’arrête sur `Presented to import customs` ou `Held at destination import office`, surveille un courrier, un e-mail ou un avis officiel de ta poste locale. Voir les [colis retenus à la douane](guide:customs).

## Colis EMS retardé, bloqué ou manquant

- **Silence entre export et import :** les dépêches aériennes internationales restent souvent plusieurs jours sans scan entre `Departed from export office` et `Arrived at destination import office`, en particulier lors des pics saisonniers ou en file d’attente d’admission douanière. Voir [pourquoi le suivi ne se met plus à jour](guide:tracking-not-updating).
- **C’est ta poste locale qui aide, pas l’UPU :** comme indiqué sur [EMS Tracking](https://www.ems.post/en/global-network/tracking), l’Unité EMS et l’UPU n’assurent pas de suivi par téléphone et n’interviennent pas dans les litiges entre opérateurs postaux et clients. Pour un envoi EMS manquant, la [page de contacts EMS](https://www.ems.post/en/about-us/contacts-0) renvoie vers le service client ou le centre d’appels EMS de ton pays, qui peut répondre aux questions générales sur EMS, retrouver ton numéro de suivi et aider à localiser l’envoi. Voir [colis perdu](guide:lost-parcel), [colis endommagé](guide:damaged-parcel), [colis indiqué livré mais non reçu](guide:delivered-not-received) et [colis retourné à l’expéditeur](guide:returned-to-sender).

## Service client EMS et annuaire officiel des opérateurs

Parce que le service EMS de chaque pays est exploité par sa poste nationale désignée, [EMS Contact Us](https://www.ems.post/en/about-us/contacts-0) publie un annuaire consultable de A à Z des pages de service client et centres d’appels EMS nationaux classés par région (Afrique, Pays arabes, Asie-Pacifique, Caraïbes, Europe, Amérique latine et Amérique du Nord) :

- **Trouver le téléphone et l’assistance de ton opérateur EMS local :** [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) et [ems.post/en/global-network/ems-operators](https://www.ems.post/en/global-network/ems-operators).
- **Suivi public mondial :** [items.ems.post](https://items.ems.post/) (mode d’emploi sur [ems.post/en/global-network/tracking](https://www.ems.post/en/global-network/tracking)).
- **Aucune assistance téléphonique centrale de l’UPU :** ni l’Unité EMS ni l’Union postale universelle ne traitent par téléphone les demandes relatives à un colis individuel.

> Méfie-toi des faux SMS affirmant qu’un « colis EMS » est bloqué tant que tu ne paies pas une petite somme via un lien inconnu. Vérifie toujours les frais de douane directement sur le site officiel de ton opérateur postal national avec ton code EMS de 13 caractères. Voir les [arnaques aux SMS de livraison](guide:delivery-scam-texts).

## Suivre un colis EMS avec Peek

Lorsque tu saisis un code S10 de 13 caractères commençant par `E` (`EA482917367JP` ou `EE739184627CN`, inventés) dans [Peek](/), Peek l’oriente normalement en premier vers l’opérateur postal d’origine indiqué par les deux dernières lettres (comme [Japan Post](carrier:japan-post) ou [China Post](carrier:china-post)), car EMS ne possède pas de règle de détection automatique autonome. Pour interroger plutôt le suivi mondial de la Coopérative **EMS**, appuie sur « Changer » à l’ajout ou sur « Changer de transporteur » sur la fiche du colis puis choisis **EMS** (rechercher « Express Mail Service » ou « EMS Post » le trouve aussi). Peek reconnaît également directement comme EMS les liens collés `items.ems.post/api/publicTracking/track?itemId=`.

Peek interroge directement `items.ems.post` après avoir vérifié que le code est un numéro S10 en `E` avec chiffre de contrôle valide, et affiche l’étape (« Pris en charge », « En transit », « À la douane », « En livraison », « Tentative de livraison », « Problème signalé », « Retourné à l’expéditeur », « Livré »), le libellé EMS en anglais et le bureau. EMS fournit les horaires de scan sans fuseau horaire : Peek date chaque scan sur le fuseau horaire de son bureau (celui de Tokyo pour `TOKYO INTERNATIONAL`, celui de Paris pour `ROISSY CDG`), et un scan sans lieu sur le fuseau du scan voisin, puis affiche le tout à l’heure de ton appareil. Le tableau EMS ne fournit ni date de livraison estimée, ni poids, ni nom du signataire, ni heure de livraison séparée.

Tant que le colis reste ouvert dans Peek, ou le jour de la livraison si les notifications sont activées, Peek vérifie jusqu’à toutes les 10 minutes ; le jour où EMS affiche `Out for delivery`, toutes les 2 minutes à partir de 6 h sur le fuseau horaire du lieu du scan le plus récent, tant que le suivi EMS répond. Sinon, Peek vérifie toutes les heures. Après 12 heures avec uniquement « Étiquette créée » ou « Pris en charge », ou deux jours sans nouvelles, Peek passe à une vérification par heure quelle que soit ta manière de suivre le colis, et toutes les 6 heures après sept jours à « Étiquette créée ». Lorsque le suivi EMS ne répond pas et que Peek suit le colis uniquement via des services de suivi, il vérifie toutes les heures, toutes les 3 heures après une journée calme, et jusqu’à toutes les 15 minutes le jour de la livraison tant que le colis est ouvert ou que les notifications sont activées.

Lorsque le scan le plus récent date de quatre jours et que le colis est toujours sur « Étiquette créée », « Pris en charge » ou « En transit », Peek le signale avec le jour de ce scan, par exemple « Aucune mise à jour depuis lun. 12 oct. ». La fiche du colis indique alors « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger EMS. », avec un lien « Demande de l’aide à EMS » vers l’annuaire de contacts EMS. Après trois semaines sans nouveau scan, elle affiche « Rien de nouveau depuis plus de trois semaines. Si le colis n’est toujours pas arrivé, contacte le vendeur. Peek continue d’interroger EMS. »

Tu peux choisir des alertes pour « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Peek suit uniquement les mises à jour de suivi et ne peut ni dédouaner un colis, ni payer des frais d’importation, ni contacter ta poste locale à ta place.

## Questions sur le suivi EMS

### À quoi ressemble un numéro de suivi EMS valide ?

Un numéro de suivi EMS est un code UPU S10 de 13 caractères commençant par `E`, suivi d’une deuxième lettre, de 9 chiffres (dont un chiffre de contrôle final) et du code ISO à 2 lettres du pays d’envoi — par exemple `EA482917367JP` ou `EE739184627CN` (inventés).

### Qui livre mon colis EMS dans mon pays ?

Les colis EMS sont livrés par l’opérateur postal national désigné de ton pays (ou sa filiale express), comme [USPS](carrier:usps) aux États-Unis, [Royal Mail](carrier:royal-mail) / Parcelforce au Royaume-Uni, [La Poste](carrier:la-poste) en France, [Poste Italiane](carrier:poste-italiane) en Italie ou [Australia Post](carrier:australia-post) en Australie.

### Pourquoi mon colis EMS ne se met-il plus à jour après `Departed from export office` ?

Après `Departed from export office`, le colis est en transit aérien international ou en attente de prise en charge au centre d’échange postal international du pays de destination. Le suivi reprend dès que la poste de destination scanne `Arrived at destination import office`.

### Qui contacter si un colis EMS est bloqué ou perdu ?

L’UPU et l’Unité EMS ne donnent pas de suivi par téléphone et n’interviennent pas dans les litiges entre postes et clients. Utilise [ems.post/en/about-us/contacts-0](https://www.ems.post/en/about-us/contacts-0) pour trouver le service client ou le centre d’appels EMS de ton pays, qui pourra t’aider à localiser ton envoi.

:::sources
- [Coopérative EMS : site officiel](https://www.ems.post/en) – présentation de la Coopérative Express Mail Service de l’UPU et réseau postal mondial
- [Coopérative EMS : guide de suivi](https://www.ems.post/en/global-network/tracking) – format S10 de 13 caractères, non-intervention de l’UPU/Unité EMS et absence de suivi téléphonique
- [Coopérative EMS : contacts mondiaux des opérateurs](https://www.ems.post/en/about-us/contacts-0) – annuaire A–Z des services clients EMS nationaux par région (page d’aide liée par Peek)
- [Coopérative EMS : opérateurs A–Z](https://www.ems.post/en/global-network/ems-operators) – réseau des opérateurs postaux nationaux désignés
- [Portail public de suivi EMS](https://items.ems.post/) – recherche publique mondiale des envois EMS
- [Universal Parcel Scraper : ems](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/ems) – validation du chiffre de contrôle S10 en `E`, règle de lien `items.ems.post`, table des 18 statuts de la Coopérative EMS et datation de chaque scan sur le fuseau de son bureau
:::
