---
title: Suivi Japan Post : numéros EMS et statuts expliqués
description: Suivre un colis ou EMS Japan Post depuis le Japon : format des numéros JP, sens des statuts, livraison locale, douane et contacts.
slug: suivi-japan-post
published: 2026-11-22
updated: 2026-11-22
---

Japan Post (日本郵便, *Japan Post Co., Ltd.*) est l’opérateur postal du Japon, filiale de Japan Post Holdings, qui comptait 24 115 bureaux de poste au 31 mars 2026. Elle expédie à l’étranger les envois EMS, *International Air Packets* et colis internationaux, ainsi que les colis Yu-Pack au Japon. Suis ton envoi sur le [service Track & Trace](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en) de Japan Post (jusqu’à dix numéros à la fois) ou colle le numéro dans la case ci-dessus. Commande passée dans une boutique japonaise ? Demande le numéro au vendeur.

## Numéro de suivi Japan Post : à quoi il ressemble

Un envoi international depuis le Japon comporte 13 caractères : deux lettres, neuf chiffres et `JP`. Le dernier des neuf chiffres est un chiffre de contrôle. Avec des chiffres inventés :

| Format (inventé) | Ce que c’est |
| --- | --- |
| `EN482917367JP` | Un envoi EMS, *International Air Packet*, colis international ou lettre recommandée envoyé depuis le Japon. |
| `482917364823` | 12 chiffres : un envoi national au Japon, par exemple un colis Yu-Pack. |
| `48291736485` | 11 chiffres : également national, longueur indiquée par le suivi de Japan Post pour le courrier et les colis. |

Le suivi de Japan Post n’accepte que ce format pour le courrier international, et non des numéros comme `CNG` ou `LP` suivis de 14 chiffres. Un code-barres commençant par `U` sur un petit paquet ou sa déclaration en douane (CN22) n’est pas non plus un numéro de suivi : cet envoi voyage sans suivi. Un numéro se terminant par d’autres lettres, comme `US`, appartient à la poste d’un autre pays. Pas de numéro ? Voir [où trouver ton numéro de suivi](guide:find-tracking-number).

## Signification des statuts de suivi Japan Post

Le suivi anglais de Japan Post affiche le scan le plus ancien en premier, avec la date, l’heure, le bureau et la préfecture ou le pays. Les libellés que Peek connaît, dans l’ordre habituel :

| Japan Post indique | Ce que ça veut dire pour toi |
| --- | --- |
| `Posting/Collection` | L’expéditeur a déposé le colis ou Japan Post l’a collecté. |
| `En route` | En transit entre les bureaux de Japan Post. |
| `Arrival at outward office of exchange` | Arrivé au bureau d’échange chargé d’expédier vers l’étranger. |
| `Dispatch from outward office of exchange` | A quitté le Japon (ou le pays d’origine pour un envoi vers le Japon). |
| `Arrival at inward office of exchange` | Arrivé au bureau d’échange du pays de destination. |
| `Item presented to import Customs`, `In Customs` | En contrôle douanier dans le pays de destination. |
| `Item held at inward Office of Exchange` | Retenu au bureau d’échange d’arrivée. |
| `Item returned from import Customs` | La douane l’a rendu à la poste : le colis repart. |
| `Departure from inward office of exchange` | En route vers la livraison. |
| `Item out of sorting centre` | A quitté un centre de tri du pays de destination. |
| `Arrival`, `Processing at delivery Post Office` | Arrivé au bureau de poste qui assure la livraison. |
| `Item held at delivery depot/delivery office` | Au bureau de distribution ; la ligne ne précise pas si le retrait est possible. |
| `Item out for physical delivery` | En livraison. |
| `Final delivery` | Livré. |
| `Final delivery - Collected at counter` | Retiré au guichet d’un bureau de poste au Japon. |
| `Retention` | Conservé dans un bureau de poste au Japon après une absence, pour retrait ou jusqu’à une date choisie ; la ligne ne dit pas lequel. |
| `Returned to sender` | En cours de retour à l’expéditeur. |

Le courrier arrivant au Japon présente trois autres lignes : `Transported in bond` signifie qu’il voyage encore sous contrôle douanier vers le bureau d’échange chargé de ta zone ; `Arrived in bond` qu’il y est arrivé. Le dédouanement continue jusqu’à `Dispatched from Exchange Office`. Plus dans [signification des statuts de suivi](guide:tracking-statuses).

## Colis Japan Post à l’étranger : qui livre et douane

Après `Dispatch from outward office of exchange`, la poste de ton pays prend le relais. Pour EMS, Japan Post renvoie vers le site de cette poste, où le suivi est plus rapide — par exemple [La Poste](carrier:la-poste), [USPS](carrier:usps), Parcelforce au Royaume-Uni, An Post, [Postes Canada](carrier:canada-post), [Australia Post](carrier:australia-post) et NZ Post. Utilise le même numéro `JP`. Les tentatives de livraison et le retrait suivent alors les règles de ta poste : voir [livraison manquée](guide:missed-delivery).

**Affiché comme livré.** Un *International Air Packet* est déposé en boîte aux lettres sans signature, sauf taxes à payer, et s’affiche livré. Japan Post avertit aussi qu’un EMS vers le Royaume-Uni ou la France ayant manqué son destinataire peut apparaître livré alors qu’il attend au bureau de poste le plus proche. Un EMS vers l’Allemagne peut apparaître retourné ou livré alors qu’un commissionnaire en douane ou un autre transporteur le détient. Comme c’est ta poste qui a livré, adresse-toi d’abord à elle : voir [livré mais non reçu](guide:delivered-not-received).

**États-Unis.** Depuis le 15 octobre 2026, Japan Post accepte de nouveau le courrier vers les États-Unis dans la quasi-totalité des bureaux de poste. Les marchandises jusqu’à 2 500 $ US doivent être enregistrées au préalable chez Zonos ou SafePackage (les prestataires recommandés par Japan Post) avec droits et taxes prépayés ; l’étiquette porte alors la mention DDP et un numéro de déclaration à 13 chiffres. Jusqu’au 19 janvier 2027, les cadeaux entre particuliers jusqu’à 100 $ US en sont dispensés. Au-dessus de 2 500 $ US, l’importateur paie aux États-Unis.

**Douane de l’UE.** Le Japon étant hors UE, tout colis passe la douane dans ton pays. Depuis le 1er juillet 2026, les achats en ligne provenant hors UE dans des commandes jusqu’à 150 € supportent un droit de douane européen de 3 € par article. Depuis le 1er novembre 2026, des frais de traitement de l’Union de 2 € par article s’appliquent aussi, quelle que soit la valeur. Un article désigne les marchandises partageant une même classification tarifaire, description et, si requis, origine. Ailleurs, ce sont les règles de ton pays qui s’appliquent : voir [colis bloqué à la douane](guide:customs).

## Recevoir un colis Japan Post au Japon

**Douane.** Le courrier venu de l’étranger est dédouané dans l’un des six bureaux d’échange du Japon. Si des droits sont dus, tu les paies à la livraison ou au bureau de poste avec l’avis de taxation remis par Japan Post. Au-delà de 200 000 ¥, une déclaration d’importation est nécessaire.

**Livraison manquée.** Un colis venu de l’étranger attend 15 jours au bureau de poste distributeur, ou un mois si des droits sont dus : retire-le là-bas ou demande une nouvelle livraison. Le formulaire anglais de Japan Post accepte le numéro à 6 chiffres ou de 11 à 13 chiffres figurant sur l’avis et ne relivre qu’à domicile ; pour un autre lieu, appelle le `0570-046-111`. Voir [colis non retirés](guide:uncollected-parcel).

## Suivi Japan Post bloqué, perdu ou retourné

**Silencieux après le départ du Japon.** Les scans proviennent ensuite de ta poste nationale. Japan Post prévient que les données venues de l’étranger peuvent être partielles ou tardives, et que les lignes peuvent s’afficher dans le désordre à cause des décalages horaires. Vérifie le suivi de ta poste avec le même numéro : voir [pourquoi un suivi ne se met plus à jour](guide:tracking-not-updating).

**Aucun suivi.** Les *Small Packets* n’en ont pas, et depuis le 31 décembre 2025 Japan Post n’accepte plus les petits paquets de marchandises en recommandé. Un petit paquet suivi voyage en *International Air Packet*. Le courrier de surface n’est suivi qu’en tant que colis international.

**Perdu ou abîmé.** L’expéditeur demande une recherche par formulaire au bureau de poste de dépôt (et non par téléphone) : sous 6 mois pour les lettres recommandées, valeurs déclarées et colis internationaux ; pour EMS, le pays de destination fixe le délai. Une indemnisation couvre les lettres recommandées et assurées, les colis internationaux et EMS (gratuite jusqu’à 20 000 ¥, assurable jusqu’à 2 millions ¥) ; *International Air Packet* n’en prévoit pas. Pour une commande en ligne, adresse-toi à la boutique : voir [colis perdus](guide:lost-parcel). S’il arrive abîmé ou incomplet, conserve tout et signale-le à ton bureau de poste local : voir [colis abîmés](guide:damaged-parcel).

**Retourné.** Japan Post signale qu’une saisie de retour erronée à l’étranger peut laisser `Returned to sender` affiché après livraison ; la ligne indique alors le pays de livraison plutôt qu’une préfecture. Japan Post demande aussi aux expéditeurs de ne pas poster de commandes IOSS sous 150 € vers l’Allemagne, l’Autriche, la Belgique, le Danemark, la Finlande, la France, le Luxembourg ou le Portugal, car les douanes ou postes locales risquent de les renvoyer. Voir [retourné à l’expéditeur](guide:returned-to-sender).

## Service client et téléphone Japan Post

Lignes téléphoniques de Japan Post (injoignables depuis l’étranger) :

- **Customer Service Center :** `0120-5931-55` (gratuit), ou `0570-046-666` depuis un mobile (payant).
- **En anglais :** `0570-046-111` (payant), également pour les demandes de nouvelle livraison.
- **Horaires :** tous les jours, de 8 h à 21 h heure du Japon.

Depuis l’étranger, adresse-toi à l’expéditeur ou à la boutique, qui peut lancer une recherche au bureau de poste d’envoi. Japan Post précise ne pas pouvoir fournir plus d’informations de suivi que son site web.

## Suivre un colis Japan Post avec Peek

[Peek](/) suit plus de 4 500 transporteurs et reconnaît aussitôt deux lettres, neuf chiffres et `JP` comme Japan Post lorsque le chiffre de contrôle est juste (« Transporteur détecté ») ; « Changer » permet d’en choisir un autre. Si le chiffre de contrôle est faux, Peek affiche « Le chiffre de contrôle de ce numéro ne correspond pas. » : vérifie ton message d’expédition ou choisis « Suivre tel quel ». Les liens vers le suivi Japan Post fonctionnent aussi. Un numéro commençant par `U` n’est pas reconnu comme Japan Post et Peek ne l’y recherche pas, car Japan Post ne suit pas les numéros en `U`.

Un numéro national à 12 chiffres n’appartient pas exclusivement à Japan Post. Colle-le tout de même : Peek interroge les transporteurs compatibles (Yamato et Japan Post en tête si tu cherches depuis le Japon) et affiche « Japan Post a ce colis. » dès que Japan Post le connaît. Pour un numéro à 11 chiffres, Peek n’interroge pas Japan Post : choisis-le via « Changer ». Classé sous Japan Post, un colis affiche « En attente de Japan Post » jusqu’au premier scan.

Peek affiche chaque scan dans l’anglais de Japan Post avec le bureau et le lieu, ainsi que le service et l’heure de livraison, et classe les étapes sous ses propres statuts (« En transit », « À la douane », etc.). Il laisse de côté la colonne Details de Japan Post, qui peut indiquer un motif comme une adresse inconnue. Les horaires sont lus sur l’heure locale du lieu indiqué (ou des scans voisins) et convertis dans ton fuseau horaire (« Heures dans ton fuseau horaire : » suivi de son nom). Pour un envoi national avec un jour de livraison choisi par l’expéditeur, Peek affiche ce jour et son créneau comme livraison prévue jusqu’à la livraison ou jusqu’à ce que la journée soit passée.

Depuis de nombreux pays, Peek interroge aussi ta poste nationale avec le même numéro `JP` : USPS aux États-Unis, [Royal Mail](carrier:royal-mail) au Royaume-Uni (Parcelforce pour les numéros commençant par `E`, comme indiqué par Japan Post pour EMS), An Post en Irlande, Postes Canada, Australia Post, NZ Post, [DHL](carrier:dhl) en Allemagne, [La Poste](carrier:la-poste) en France et la poste de chaque autre pays de l’UE. Il bascule sur cette poste dès qu’elle publie des scans plus récents tout en conservant l’historique Japan Post. Depuis la Corée, la Chine, Hong Kong ou Singapour, aucune autre poste n’est interrogée. Inversement, lorsqu’une autre poste indique le Japon comme destination (ou aucune destination si tu suis depuis le Japon), Peek interroge Japan Post avec ce numéro, sauf s’il commence par `U`.

Tant que le colis est ouvert dans Peek — ou le jour de la livraison si les notifications sont activées — Peek interroge directement Japan Post jusqu’à toutes les 10 minutes, et jusqu’à toutes les 2 minutes dès 6 h à l’heure du dernier scan (heure du Japon au Japon, ou dès 30 minutes avant l’ouverture d’un créneau domestique choisi) le jour où le colis passe « En livraison », tant que le suivi de Japan Post répond (une fois qu’une autre poste prend le relais, Peek suit la cadence de cette poste) ; sinon, ainsi qu’après deux jours sans nouveauté (12 heures tant que le colis reste sur « Étiquette créée » ou « Pris en charge »), il vérifie toutes les heures. Pour les notifications, choisis « Chaque scan », « Étapes importantes » ou « Livraison seulement ». Après quatre jours sans scan en route, hors douane ou problème signalé, Peek affiche la date du dernier scan (« Aucune mise à jour depuis … ») et indique « Un colis reste souvent silencieux pendant un long vol, à la douane ou le week-end. Peek continue d’interroger Japan Post. », avec le lien « Demande de l’aide à Japan Post ». Peek ne peut contacter ni Japan Post, ni ta poste, ni la boutique pour toi.

## Questions sur le suivi Japan Post

### Qu’est-ce que l’EMS de Japan Post ?

C’est le service international le plus rapide de Japan Post, pour documents et marchandises jusqu’à 30 kg vers plus de 120 pays et territoires, avec suivi. Ses numéros se terminent par `JP`.

### Pourquoi mon suivi Japan Post ne se met-il plus à jour ?

Si la dernière ligne est `Dispatch from outward office of exchange`, le colis a quitté le Japon : vérifie le suivi de ta poste nationale avec le même numéro et prévois le temps du vol et de la douane.

### Puis-je suivre un petit paquet Japan Post ?

Seulement s’il a été expédié en *International Air Packet*. Un *Small Packet* ne dispose d’aucun suivi, et un code-barres commençant par `U` n’est pas un numéro de suivi. Demande au vendeur quel service il a utilisé.

### Combien de temps met Japan Post pour livrer ?

Environ 5 à 21 jours pour un *International Air Packet*, selon Japan Post. EMS est le service le plus rapide ; l’outil des tarifs et délais sur `post.japanpost.jp` indique la durée selon la destination.

:::sources
- [Japan Post: Track & Trace Service](https://trackings.post.japanpost.jp/services/srv/search/input?locale=en) – suivi, exemples de numéros
- [Japan Post: Item numbers on labels](https://trackings.post.japanpost.jp/slipEn.html) – numéro Yu-Pack à 12 chiffres
- [Japan Post: Notices Concerning EMS Track & Trace Service](https://www.post.japanpost.jp/service/send/oversea/information/ems_search_en.html) – numéros, ordre des scans, scans à l’étranger, colonne Details, retours, lignes sous douane
- [Japan Post: Link to Overseas Post Offices](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/link_en.html) – postes partenaires pour EMS
- [Japan Post: Products and services comparative table](https://www.post.japanpost.jp/service/send/oversea/list/hikaku_en.html) – suivi selon le service
- [Japan Post: International Air Packet](https://www.post.japanpost.jp/service/send/oversea/list/delivery/airpacket_en.html) – dépôt en boîte aux lettres, délai de livraison
- [Japan Post: EMS](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/index_en.html) – service, outil de délais
- [Japan Post: EMS Insurance Program](https://www.post.japanpost.jp/service/send/oversea/list/delivery/ems/damage_en.html) – couverture
- [Japan Post: Mail destined for the United States (23 October 2026)](https://www.post.japanpost.jp/service/send/oversea/information/2026/1023_01_en.html) – règles États-Unis
- [Japan Post: EU duty-free exemption removal (1 July 2026)](https://www.post.japanpost.jp/service/send/oversea/information/2026/0701_01_en.html) – commandes IOSS
- [Japan Post: Searches for undelivered items](https://www.post.japanpost.jp/question/oversea/18_en.html) – recherches
- [Japan Post: Searches for EMS](https://www.post.japanpost.jp/question/oversea/138_en.html) – recherches EMS
- [Japan Post: Compensation](https://www.post.japanpost.jp/question/oversea/21_en.html) – envois couverts
- [Japan Post: Compensation claims](https://www.post.japanpost.jp/question/oversea/17_en.html) – dommages
- [Japan Post: Holding period for parcels from overseas](https://www.post.japanpost.jp/question/oversea/23_en.html) – délai de garde
- [Japan Post: Delivery Flow](https://www.post.japanpost.jp/service/send/oversea/use/flow/receive_en.html) – douane au Japon
- [Japan Post: Application for redelivery](https://www.post.japanpost.jp/receive/redelivery_form/) – nouvelle livraison
- [Japan Post: Yu-Pack](https://www.post.japanpost.jp/service/you_pack/index_en.html) – jour de livraison choisi
- [Japan Post: Inquiry by Phone](https://www.post.japanpost.jp/question/oversea/tel_en.html) – numéros, horaires
- [Japan Post: Corporate Information](https://www.post.japanpost.jp/about/profile_en.html) – entreprise, bureaux de poste
- [European Commission: EU customs reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) – droit de 3 €, frais de traitement de 2 €
- [European Commission: Guidance on the €3 customs duty and Union handling fee (PDF)](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – par article, définition d’un article
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – libellés des scans, champs lus par Peek, postes interrogées et fréquence de vérification
:::
